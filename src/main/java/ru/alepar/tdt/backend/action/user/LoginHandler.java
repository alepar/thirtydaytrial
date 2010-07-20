package ru.alepar.tdt.backend.action.user;

import ru.alepar.tdt.backend.action.core.ActionHandler;
import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.gwt.client.action.user.Login;

/**
 * User: alepar
 * Date: Jul 20, 2010
 * Time: 11:28:12 PM
 */
public class LoginHandler implements ActionHandler<Login.LoginResponse> {

    @Override
    public Login.LoginResponse execute() {
        return new Login.LoginResponse(new UserAccount("dummy_id", "dummy_login", "dummy_email"));
    }
    
}
