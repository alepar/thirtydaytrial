package ru.alepar.tdt.gwt.client.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.alepar.tdt.gwt.client.TdtServiceAsync;
import ru.alepar.tdt.gwt.client.action.auth.AuthAction;

/**
 * User: alepar
 * Date: Jul 22, 2010
 * Time: 6:34:52 AM
 */
public class AuthCheck {

    private final Display display;
    private final TdtServiceAsync service;

    public AuthCheck(Display display, TdtServiceAsync service) {
        this.display = display;
        this.service = service;
    }

    public void run() {
        service.execute(new AuthAction(), new GotAuth());
    }

    public interface Display {
        void onSuccessAuth(AuthAction.AuthResponse authResponse);
        void onNotLoggedIn(AuthAction.AuthResponse authResponse);
        void onFailure(Throwable throwable);
    }

    private class GotAuth implements AsyncCallback<AuthAction.AuthResponse> {
        @Override
        public void onFailure(Throwable throwable) {
            display.onFailure(throwable);
        }

        @Override
        public void onSuccess(AuthAction.AuthResponse authResponse) {
            if(authResponse.isLoggedId()) {
                display.onSuccessAuth(authResponse);
            } else {
                display.onNotLoggedIn(authResponse);
            }
        }
    }
}
