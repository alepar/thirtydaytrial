package ru.alepar.tdt.backend.logic;

import com.google.appengine.api.users.User;
import ru.alepar.gwt.tdt.client.action.TdtVoidResponse;
import ru.alepar.gwt.tdt.client.action.user.AddUserAction;
import ru.alepar.tdt.backend.dao.DaoSession;
import ru.alepar.tdt.backend.dao.DaoSessionFactory;
import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.backend.model.UserEmail;
import ru.alepar.tdt.backend.model.UserId;

/**
 * User: looser
 * Date: 11.07.2010
 */
public class AddUserAccount implements Command<TdtVoidResponse> {
    private final DaoSessionFactory sessionFactory;
    private final UserId id;
    private final UserEmail email;
    private final AddUserAction action;

    public AddUserAccount(DaoSessionFactory sessionFactory, User user, AddUserAction action) {
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
