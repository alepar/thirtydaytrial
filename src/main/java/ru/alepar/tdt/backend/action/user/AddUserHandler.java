package ru.alepar.tdt.backend.action.user;

import ru.alepar.tdt.backend.action.auth.AuthInfo;
import ru.alepar.tdt.backend.action.core.ActionHandler;
import ru.alepar.tdt.backend.dao.core.DaoSession;
import ru.alepar.tdt.backend.dao.core.DaoSessionFactory;
import ru.alepar.tdt.backend.model.user.UserAccount;
import ru.alepar.tdt.backend.model.user.UserEmail;
import ru.alepar.tdt.backend.model.user.UserId;
import ru.alepar.tdt.backend.security.Allow;
import ru.alepar.tdt.gwt.client.action.core.TdtVoidResponse;
import ru.alepar.tdt.gwt.client.action.user.AddUser;

import static ru.alepar.tdt.backend.security.SecurityLevel.*;

/**
 * User: looser
 * Date: 11.07.2010
 */
@Allow(ADMIN)
public class AddUserHandler implements ActionHandler<TdtVoidResponse> {
    private final DaoSessionFactory sessionFactory;
    private final UserId id;
    private final UserEmail email;
    private final AddUser action;

    public AddUserHandler(DaoSessionFactory sessionFactory, AuthInfo authInfo, AddUser action) {
        this.sessionFactory = sessionFactory;
        id = new UserId(authInfo.getUser().getUserId());
        email = new UserEmail(authInfo.getUser().getEmail());
        this.action = action;
    }

    public TdtVoidResponse execute() {
        DaoSession session = sessionFactory.session();
        session.open();
        try {
            session.userAccount().insert(new UserAccount(id, action.getLogin(), email));
        } finally {
            session.close();
        }
        return new TdtVoidResponse();
    }
}
