package ru.alepar.tdt.gwt.client.action.user;

import ru.alepar.tdt.backend.action.core.MapTo;
import ru.alepar.tdt.backend.model.UserLogin;
import ru.alepar.tdt.gwt.client.action.core.TdtAction;
import ru.alepar.tdt.gwt.client.action.core.TdtVoidResponse;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
@MapTo(ru.alepar.tdt.backend.action.user.AddUserHandler.class)
public class AddUser implements TdtAction<TdtVoidResponse> {
    UserLogin login;

    public AddUser() {
    }

    public AddUser(UserLogin login) {
        this.login = login;
    }

    public UserLogin getLogin() {
        return login;
    }

    public void setLogin(UserLogin login) {
        this.login = login;
    }

}
