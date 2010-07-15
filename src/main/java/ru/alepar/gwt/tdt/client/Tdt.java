package ru.alepar.gwt.tdt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import ru.alepar.gwt.tdt.client.event.EditTrialHistoryEvent;
import ru.alepar.gwt.tdt.client.event.TrialChangedEvent;
import ru.alepar.gwt.tdt.client.model.Trial;
import ru.alepar.gwt.tdt.client.presenter.TrialEditor;
import ru.alepar.gwt.tdt.client.presenter.TrialsTable;
import ru.alepar.gwt.tdt.client.view.TrialEditorDisplay;
import ru.alepar.gwt.tdt.client.view.TrialsTableDisplay;

public class Tdt implements EntryPoint {

    private static int trialId;

    private static Integer nextId() {
        return ++trialId;
    }

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final HandlerManager handlerManager = new HandlerManager(null);

        final Button button = new Button("add");
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                handlerManager.fireEvent(new EditTrialHistoryEvent(new Trial(nextId())));                
            }
        });
        RootPanel.get("add_trial").add(button);

        final TrialEditorDisplay trialEditorDisplay = new TrialEditorDisplay();
        final TrialEditor trialEditor = new TrialEditor(handlerManager, trialEditorDisplay);
        handlerManager.addHandler(EditTrialHistoryEvent.TYPE, trialEditor);
        RootPanel.get("editor_trial").add(trialEditorDisplay);

        final TrialsTableDisplay trialsTableDisplay = new TrialsTableDisplay();
        final TrialsTable trialsTable = new TrialsTable(trialsTableDisplay);
        handlerManager.addHandler(TrialChangedEvent.TYPE, trialsTable);
        RootPanel.get("table_trial").add(trialsTableDisplay);

        AuthService.App.getInstance().getAuth(new AuthAsyncCallBack(RootPanel.get("signin").getElement()));
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
