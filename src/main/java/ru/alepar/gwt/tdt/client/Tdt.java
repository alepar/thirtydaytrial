package ru.alepar.gwt.tdt.client;

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
import ru.alepar.gwt.tdt.client.event.EditTrialEvent;
import ru.alepar.gwt.tdt.client.event.TrialChangedEvent;
import ru.alepar.gwt.tdt.client.history.EditTrialHistoryEvent;
import ru.alepar.gwt.tdt.client.presenter.TrialEditor;
import ru.alepar.gwt.tdt.client.presenter.TrialsTable;
import ru.alepar.gwt.tdt.client.view.TrialEditorDisplay;
import ru.alepar.gwt.tdt.client.view.TrialsTableDisplay;
import ru.alepar.tdt.backend.model.Trial;

public class Tdt implements EntryPoint, ValueChangeHandler<String> {

    private static long trialId;

    private final HandlerManager eventBus = new HandlerManager(null);

    private static Long nextId() {
        return ++trialId;
    }

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final Button button = new Button("add");
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                eventBus.fireEvent(new EditTrialEvent(new Trial(nextId())));                
            }
        });
        RootPanel.get("add_trial").add(button);

        final TrialEditorDisplay trialEditorDisplay = new TrialEditorDisplay(RootPanel.get("editor_trial"));
        final TrialEditor trialEditor = new TrialEditor(eventBus, trialEditorDisplay);
        eventBus.addHandler(EditTrialEvent.TYPE, trialEditor);

        final TrialsTableDisplay trialsTableDisplay = new TrialsTableDisplay();
        final TrialsTable trialsTable = new TrialsTable(eventBus, trialsTableDisplay);
        eventBus.addHandler(TrialChangedEvent.TYPE, trialsTable);
        eventBus.addHandler(EditTrialHistoryEvent.TYPE, trialsTable);
        RootPanel.get("table_trial").add(trialsTableDisplay);

        History.addValueChangeHandler(this);

        AuthService.App.getInstance().getAuth(new AuthAsyncCallBack(RootPanel.get("signin").getElement()));
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> historyEvent) {
        eventBus.fireEvent(new EditTrialHistoryEvent(historyEvent.getValue()));
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
