package ru.alepar.gwt.tdt.client.action.user;

import ru.alepar.gwt.tdt.client.action.TdtAction;
import ru.alepar.gwt.tdt.client.action.TdtVoidResponse;
import ru.alepar.tdt.backend.logic.core.MapTo;
import ru.alepar.tdt.backend.model.UserLogin;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
@MapTo("ru.alepar.tdt.backend.logic.AddUserAccount")
public class AddUserAction implements TdtAction<TdtVoidResponse> {
    UserLogin login;

    public AddUserAction() {
    }

    public AddUserAction(UserLogin login) {
        this.login = login;
    }

    public UserLogin getLogin() {
        return login;
    }

    public void setLogin(UserLogin login) {
        this.login = login;
    }

    @Override
    public TdtVoidResponse execute() {
        throw new RuntimeException("not implemented yet");
    }
}
