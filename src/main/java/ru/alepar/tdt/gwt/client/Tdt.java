package ru.alepar.tdt.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import ru.alepar.tdt.backend.model.trial.Trial;
import ru.alepar.tdt.backend.model.trial.UserTrial;
import ru.alepar.tdt.gwt.client.action.gcal.ScheduleEvent;
import ru.alepar.tdt.gwt.client.action.trial.GetTrials;
import ru.alepar.tdt.gwt.client.event.EditUserTrialEvent;
import ru.alepar.tdt.gwt.client.event.UserTrialChangedEvent;
import ru.alepar.tdt.gwt.client.event.UserTrialDeletedEvent;
import ru.alepar.tdt.gwt.client.history.EditUserTrialHistoryEvent;
import ru.alepar.tdt.gwt.client.history.HistoryAwareHandlerManager;
import ru.alepar.tdt.gwt.client.history.HistoryEventFactory;
import ru.alepar.tdt.gwt.client.history.HomeHistoryEvent;
import ru.alepar.tdt.gwt.client.presenter.AuthCheck;
import ru.alepar.tdt.gwt.client.presenter.GoogleDataIntegrator;
import ru.alepar.tdt.gwt.client.presenter.TrialEditor;
import ru.alepar.tdt.gwt.client.presenter.TrialsTable;
import ru.alepar.tdt.gwt.client.view.AuthCheckDisplay;
import ru.alepar.tdt.gwt.client.view.GoogleDataIntegratorDisplay;
import ru.alepar.tdt.gwt.client.view.TrialEditorDisplay;
import ru.alepar.tdt.gwt.client.view.TrialsTableDisplay;

import java.util.HashSet;

public class Tdt implements EntryPoint, ValueChangeHandler<String> {

    private final HandlerManager eventBus = new HistoryAwareHandlerManager(null);
    private final HistoryEventFactory eventFactory = new HistoryEventFactory();
    private final TdtServiceAsync tdtService = TdtService.App.getInstance();

    public void onModuleLoad() {
        if (GWT.isScript()) {
            final AuthCheckDisplay authCheckDisplay = new AuthCheckDisplay(RootPanel.get("signin")) {
                @Override
                protected void appEntryPoint() {
                    entryPoint();
                }
            };
            final AuthCheck authCheck = new AuthCheck(authCheckDisplay, tdtService);
            authCheck.run();
        } else {
            entryPoint();
        }
    }

    public void entryPoint() {
        final TdtServiceAsync service = TdtService.App.getInstance();

        //schedule event sample button
        final Button sampleButton = new Button("add gcal sample event");
        sampleButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                service.execute(new ScheduleEvent(), new ScheduleEvent.Callback() {
                    @Override
                    public void ok() {
                        Window.alert("ok!");
                    }
                });
            }
        });
        RootPanel.get("sample_event").add(sampleButton);

        //add google cal integration button
        final GoogleDataIntegratorDisplay integratorDisplay = new GoogleDataIntegratorDisplay();
        final GoogleDataIntegrator integrator = new GoogleDataIntegrator(integratorDisplay, service);
        final Button gcalIntegrateButton = new Button("integrate with gcal");
        gcalIntegrateButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                integrator.go();
            }
        });
        RootPanel.get("gcal_integrate").add(gcalIntegrateButton);

        //add trial button
        final Button addTrialButton = new Button("add");
        addTrialButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Trial trial = new Trial();
                UserTrial userTrial = new UserTrial();
                userTrial.setTrial(trial);
                
                eventBus.fireEvent(new EditUserTrialEvent(userTrial));
            }
        });
        RootPanel.get("add_trial").add(addTrialButton);

        //trial editor
        final TrialEditorDisplay trialEditorDisplay = new TrialEditorDisplay(RootPanel.get("editor_trial"));
        final TrialEditor trialEditor = new TrialEditor(eventBus, trialEditorDisplay, service);
        eventBus.addHandler(EditUserTrialEvent.TYPE, trialEditor);
        eventBus.addHandler(HomeHistoryEvent.TYPE, trialEditor);

        //trials list
        final TrialsTableDisplay trialsTableDisplay = new TrialsTableDisplay();
        final TrialsTable trialsTable = new TrialsTable(eventBus, trialsTableDisplay);
        eventBus.addHandler(UserTrialChangedEvent.TYPE, trialsTable);
        eventBus.addHandler(UserTrialDeletedEvent.TYPE, trialsTable);
        eventBus.addHandler(EditUserTrialHistoryEvent.TYPE, trialsTable);
        RootPanel.get("table_trial").add(trialsTableDisplay);

        //populta user trials list
        service.execute(new GetTrials(), new GetTrials.GotTrials() {
            @Override
            public void gotTrials(HashSet<UserTrial> userTrials) {
                for (UserTrial trial : userTrials) {
                    eventBus.fireEvent(new UserTrialChangedEvent(trial));
                }
            }
        });

        //history
        History.addValueChangeHandler(this);
        if ("".equals(History.getToken())) {
            History.newItem("home");
        }
        History.fireCurrentHistoryState();
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> historyEvent) {
        eventBus.fireEvent(eventFactory.buildEvent(historyEvent.getValue()));
    }

}
