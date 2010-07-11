package ru.alepar.tdt.backend.logic;

import ru.alepar.tdt.backend.jdo.dao.DaoSession;
import ru.alepar.tdt.backend.jdo.model.UserAccount;

/**
 * User: looser
 * Date: 11.07.2010
 */
public class AddUserAccount {
    private final DaoSession session;
    private final String id;
    private final String email;
    private final String login;

    public AddUserAccount(DaoSession session, String id, String email, String login) {
        this.session = session;
        this.id = id;
        this.email = email;
        this.login = login;
    }

    public void execute() {
        session.open();
        try {
            session.userAccount().insert(new UserAccount(id, email, login));
        } finally {
            session.close();
        }
    }
}
