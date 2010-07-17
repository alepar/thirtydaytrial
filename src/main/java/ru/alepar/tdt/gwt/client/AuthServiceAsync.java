package ru.alepar.tdt.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * User: alepar
 * Date: Jul 10, 2010
 * Time: 7:21:05 PM
 */
public interface AuthServiceAsync {

    void getAuth(AsyncCallback<AuthService.AuthResponse> async);
}
