package ru.alepar.tdt.backend.action.auth;

import com.googlecode.objectify.NotFoundException;
import ru.alepar.tdt.backend.action.core.ActionHandler;
import ru.alepar.tdt.backend.dao.core.DaoSession;
import ru.alepar.tdt.backend.dao.core.DaoSessionFactory;
import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.gwt.client.action.auth.AuthAction;
import ru.alepar.tdt.gwt.server.AuthInfo;

/**
 * User: alepar
 * Date: Jul 22, 2010
 * Time: 7:04:18 AM
 */
public class AuthActionHandler implements ActionHandler<AuthAction.AuthResponse> {

    private final DaoSessionFactory sessionFactory;
    private final AuthInfo authInfo;

    public AuthActionHandler(DaoSessionFactory sessionFactory, AuthInfo authInfo) {
        this.sessionFactory = sessionFactory;
        this.authInfo = authInfo;
    }

    @Override
    public AuthAction.AuthResponse execute() {
        UserAccount userAccount = null;
        if(authInfo.isLoggedId()) {
            DaoSession session = sessionFactory.session();
            session.open();
            try {
                userAccount = session.userAccount().find(authInfo.getUser().getUserId());    
            } catch(NotFoundException e) {
                userAccount = new UserAccount(
                        authInfo.getUser().getUserId(),
                        authInfo.getUser().getNickname(),
                        authInfo.getUser().getEmail()
                );
                session.userAccount().insert(userAccount);
            } finally {
                session.close();
            }
        }
        return new AuthAction.AuthResponse(
                authInfo.getLogInUrl(),
                authInfo.getLogOutUrl(),
                authInfo.isLoggedId(),
                authInfo.isAdmin(),
                userAccount
        );
    }
}
