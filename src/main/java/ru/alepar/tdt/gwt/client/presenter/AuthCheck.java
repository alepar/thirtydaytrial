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
        service.execute(new AuthAction(), new Callback());
    }

    public interface Display {
        void onSuccessAuth(AuthAction.Response response);
        void onNotLoggedIn(AuthAction.Response response);
        void onFailure(Throwable throwable);
    }

    private class Callback implements AsyncCallback<AuthAction.Response> {
        @Override
        public void onFailure(Throwable throwable) {
            display.onFailure(throwable);
        }

        @Override
        public void onSuccess(AuthAction.Response response) {
            if (response.isLoggedId()) {
                display.onSuccessAuth(response);
            } else {
                display.onNotLoggedIn(response);
            }
        }
    }
}
