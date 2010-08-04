package ru.alepar.tdt.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import ru.alepar.tdt.backend.model.Trial;
import ru.alepar.tdt.gwt.client.event.EditTrialEvent;
import ru.alepar.tdt.gwt.client.event.TrialChangedEvent;
import ru.alepar.tdt.gwt.client.history.EditTrialHistoryEvent;
import ru.alepar.tdt.gwt.client.history.HistoryAwareHandlerManager;
import ru.alepar.tdt.gwt.client.history.HistoryEventFactory;
import ru.alepar.tdt.gwt.client.history.HomeHistoryEvent;
import ru.alepar.tdt.gwt.client.presenter.AuthCheck;
import ru.alepar.tdt.gwt.client.presenter.TrialEditor;
import ru.alepar.tdt.gwt.client.presenter.TrialsTable;
import ru.alepar.tdt.gwt.client.view.AuthCheckDisplay;
import ru.alepar.tdt.gwt.client.view.TrialEditorDisplay;
import ru.alepar.tdt.gwt.client.view.TrialsTableDisplay;

public class Tdt implements EntryPoint, ValueChangeHandler<String> {

    private static long trialId;

    private final HandlerManager eventBus = new HistoryAwareHandlerManager(null);
    private final HistoryEventFactory eventFactory = new HistoryEventFactory();
    private final TdtServiceAsync tdtService = TdtService.App.getInstance();

    private static Long nextId() {
        return ++trialId;
    }

    /**
     * This is the entry point method.
     */
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

        //add trial button
        final Button button = new Button("add");
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Trial trial = new Trial(nextId());
                EditTrialHistoryEvent historyEvent = new EditTrialHistoryEvent();
                historyEvent.setTrialId(trial.getId());
                History.newItem(historyEvent.token());
                eventBus.fireEvent(new EditTrialEvent(trial));
            }
        });
        RootPanel.get("add_trial").add(button);

        //trial editor
        final TrialEditorDisplay trialEditorDisplay = new TrialEditorDisplay(RootPanel.get("editor_trial"));
        final TrialEditor trialEditor = new TrialEditor(eventBus, trialEditorDisplay, service);
        eventBus.addHandler(EditTrialEvent.TYPE, trialEditor);
        eventBus.addHandler(HomeHistoryEvent.TYPE, trialEditor);

        //trials list
        final TrialsTableDisplay trialsTableDisplay = new TrialsTableDisplay();
        final TrialsTable trialsTable = new TrialsTable(eventBus, trialsTableDisplay);
        eventBus.addHandler(TrialChangedEvent.TYPE, trialsTable);
        eventBus.addHandler(EditTrialHistoryEvent.TYPE, trialsTable);
        RootPanel.get("table_trial").add(trialsTableDisplay);

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
