package ru.alepar.gwt.tdt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.DOM;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import ru.alepar.gwt.tdt.client.event.TrialChangedEvent;

public class Tdt implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final HandlerManager handlerManager = new HandlerManager(null);

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
