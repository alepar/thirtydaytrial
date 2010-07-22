package ru.alepar.tdt.gwt.client.action.auth;

import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.gwt.client.action.core.TdtResponse;

/**
* User: alepar
* Date: Jul 22, 2010
* Time: 9:22:08 AM
*/
public class AuthResponse implements TdtResponse {
    private String logInUrl;
    private String logOutUrl;
    private boolean loggedId;
    private boolean admin;
    private UserAccount userAccount;

    public AuthResponse(String logInUrl, String logOutUrl, boolean loggedId, boolean admin, UserAccount userAccount) {
        this.logInUrl = logInUrl;
        this.logOutUrl = logOutUrl;
        this.loggedId = loggedId;
        this.admin = admin;
        this.userAccount = userAccount;
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

    public UserAccount getUserAccount() {
        return userAccount;
    }
}
