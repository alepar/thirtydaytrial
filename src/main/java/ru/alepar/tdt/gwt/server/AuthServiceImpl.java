package ru.alepar.tdt.gwt.server;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.alepar.tdt.gwt.client.AuthService;

import java.security.Principal;

/**
 * User: alepar
 * Date: Jul 10, 2010
 * Time: 7:21:05 PM
 */
public class AuthServiceImpl extends RemoteServiceServlet implements AuthService {

    public static final String URL_APPLICATION_ROOT = "/";

    public Principal getUserPrincipal() {
        return getThreadLocalRequest().getUserPrincipal();    
    }

    UserService getUserService() {
        return UserServiceFactory.getUserService();
    }

    String createLogInUrl() {
        return getUserService().createLoginURL(URL_APPLICATION_ROOT);
    }

    String createLogOutUrl() {
        return getUserService().createLogoutURL(URL_APPLICATION_ROOT);
    }

    String getPrincipalName() {
        return getUserPrincipal() == null ? null : getUserPrincipal().getName();
    }

    @Override
    public AuthResponse getAuth() {
        AuthResponse response = new AuthResponse();
        response.logInUrl = createLogInUrl();
        response.logOutUrl = createLogOutUrl();
        response.principalName = getPrincipalName();
        return response;
    }
}