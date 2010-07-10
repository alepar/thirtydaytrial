package ru.alepar.gwt.tdt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.DOM;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Tdt implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final Button button = new Button("Click me");
        final Label label = new Label();

        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (label.getText().equals("")) {
                    TdtService.App.getInstance().getMessage("Hello, World!", new MyAsyncCallback(label));
                } else {
                    label.setText("");
                }
            }
        });

        // Assume that the host HTML has elements defined whose
        // IDs are "slot1", "slot2".  In a real app, you probably would not want
        // to hard-code IDs.  Instead, you could, for example, search for all
        // elements with a particular CSS class and replace them with widgets.
        //
        RootPanel.get("slot1").add(button);
        RootPanel.get("slot2").add(label);

        AuthService.App.getInstance().getAuth(new AuthAsyncCallBack(RootPanel.get("signin").getElement()));
    }

    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            DOM.setInnerHTML(label.getElement(), result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
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
