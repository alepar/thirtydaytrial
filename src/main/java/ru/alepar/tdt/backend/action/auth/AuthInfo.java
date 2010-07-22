package ru.alepar.tdt.backend.action.auth;

import com.google.appengine.api.users.User;

/**
 * User: alepar
 * Date: Jul 22, 2010
 * Time: 7:10:23 AM
 */
public class AuthInfo {

    private final String logInUrl;
    private final String logOutUrl;
    private final boolean loggedId;
    private final boolean admin;
    private final User user;

    public AuthInfo(String logInUrl, String logOutUrl, boolean loggedId, boolean admin, User user) {
        this.logInUrl = logInUrl;
        this.logOutUrl = logOutUrl;
        this.loggedId = loggedId;
        this.admin = admin;
        this.user = user;
    }

    public String getLogInUrl() {
        return logInUrl;
    }

    public String getLogOutUrl() {
        return logOutUrl;
    }

    public boolean isLoggedId() {
        return loggedId;
    }

    public boolean isAdmin() {
        return admin;
    }

    public User getUser() {
        return user;
    }
}
