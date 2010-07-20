package ru.alepar.tdt.gwt.client.action.user;

import ru.alepar.tdt.backend.action.core.MapTo;
import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.gwt.client.action.core.TdtAction;
import ru.alepar.tdt.gwt.client.action.core.TdtResponse;

/**
 * User: alepar
 * Date: Jul 20, 2010
 * Time: 11:28:52 PM
 */

@MapTo(ru.alepar.tdt.backend.action.user.LoginHandler.class)
public class Login implements TdtAction<Login.LoginResponse> {
    
    public static class LoginResponse implements TdtResponse {
        UserAccount userAccount;

        public LoginResponse(UserAccount userAccount) {
            this.userAccount = userAccount;
        }

        public UserAccount getUserAccount() {
            return userAccount;
        }
    }
    
}
