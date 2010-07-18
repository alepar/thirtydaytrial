package ru.alepar.tdt.backend.action.user;

import com.google.appengine.api.users.User;
import ru.alepar.tdt.backend.action.core.ActionHandler;
import ru.alepar.tdt.backend.dao.core.DaoSession;
import ru.alepar.tdt.backend.dao.core.DaoSessionFactory;
import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.backend.model.UserEmail;
import ru.alepar.tdt.backend.model.UserId;
import ru.alepar.tdt.gwt.client.action.core.TdtVoidResponse;
import ru.alepar.tdt.gwt.client.action.user.AddUser;

/**
 * User: looser
 * Date: 11.07.2010
 */
public class  AddUserHandler implements ActionHandler<TdtVoidResponse> {
    private final DaoSessionFactory sessionFactory;
    private final UserId id;
    private final UserEmail email;
    private final AddUser action;

    public AddUserHandler(DaoSessionFactory sessionFactory, User user, AddUser action) {
        this.sessionFactory = sessionFactory;
        id = new UserId(user.getUserId());
        email = new UserEmail(user.getEmail());
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
