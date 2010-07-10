package ru.alepar.gwt.tdt.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;

import java.io.Serializable;

/**
 * User: alepar
 * Date: Jul 10, 2010
 * Time: 7:21:05 PM
 */
@RemoteServiceRelativePath("AuthService")
public interface AuthService extends RemoteService {

    AuthResponse getAuth();

    public static class AuthResponse implements Serializable {
        public String logInUrl;
        public String logOutUrl;
        public String principalName;
    }

    public static class App {
        private static final AuthServiceAsync ourInstance = (AuthServiceAsync) GWT.create(AuthService.class);

        public static AuthServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
