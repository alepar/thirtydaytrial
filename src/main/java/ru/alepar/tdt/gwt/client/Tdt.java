package ru.alepar.tdt.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import ru.alepar.tdt.gwt.client.event.EditTrialEvent;
import ru.alepar.tdt.gwt.client.event.TrialChangedEvent;
import ru.alepar.tdt.gwt.client.history.EditTrialHistoryEvent;
import ru.alepar.tdt.gwt.client.history.HistoryAwareHandlerManager;
import ru.alepar.tdt.gwt.client.history.HistoryEventFactory;
import ru.alepar.tdt.gwt.client.history.HomeHistoryEvent;
import ru.alepar.tdt.gwt.client.presenter.TrialEditor;
import ru.alepar.tdt.gwt.client.presenter.TrialsTable;
import ru.alepar.tdt.gwt.client.view.TrialEditorDisplay;
import ru.alepar.tdt.gwt.client.view.TrialsTableDisplay;
import ru.alepar.tdt.backend.model.Trial;

public class Tdt implements EntryPoint, ValueChangeHandler<String> {

    private static long trialId;

    private final HandlerManager eventBus = new HistoryAwareHandlerManager(null);
    private HistoryEventFactory eventFactory;

    private static Long nextId() {
        return ++trialId;
    }

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        eventFactory = new HistoryEventFactory();

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

        final TrialEditorDisplay trialEditorDisplay = new TrialEditorDisplay(RootPanel.get("editor_trial"));
        final TrialEditor trialEditor = new TrialEditor(eventBus, trialEditorDisplay);
        eventBus.addHandler(EditTrialEvent.TYPE, trialEditor);
        eventBus.addHandler(HomeHistoryEvent.TYPE, trialEditor);

        final TrialsTableDisplay trialsTableDisplay = new TrialsTableDisplay();
        final TrialsTable trialsTable = new TrialsTable(eventBus, trialsTableDisplay);
        eventBus.addHandler(TrialChangedEvent.TYPE, trialsTable);
        eventBus.addHandler(EditTrialHistoryEvent.TYPE, trialsTable);
        RootPanel.get("table_trial").add(trialsTableDisplay);

        History.addValueChangeHandler(this);
        if("".equals(History.getToken())) {
            History.newItem("home");
        }
        History.fireCurrentHistoryState();

        AuthService.App.getInstance().getAuth(new AuthAsyncCallBack(RootPanel.get("signin").getElement()));
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> historyEvent) {
        eventBus.fireEvent(eventFactory.buildEvent(historyEvent.getValue()));
    }

    private static class AuthAsyncCallBack implements AsyncCallback<AuthService.AuthResponse> {

        private Element element;

        private AuthAsyncCallBack(Element element) {
            this.element = element;
        }

        @Override
        public void onFailure(Throwable throwable) {
            setText("oops");
        }

        private void setText(String text) {
            DOM.setInnerHTML(element, text);
        }

        @Override
        public void onSuccess(AuthService.AuthResponse authResponse) {
            if(authResponse.principalName == null) {
                setText("<a href=\"" + authResponse.logInUrl + "\">sign in</a>");                
            } else {
                setText("<a href=\"" + authResponse.logOutUrl + "\">" + authResponse.principalName + "</a>");
            }
        }
    }
}
