package ru.alepar.tdt.backend.dao.db;

import ru.alepar.tdt.backend.dao.UserAccountDao;
import ru.alepar.tdt.backend.model.UserAccount;

/**
 * User: looser
 * Date: 10.07.2010
 */
public class UserAccountJdoDao extends BaseJdoDao implements UserAccountDao {
    public UserAccountJdoDao(JdoSession session) {
        super(session);
    }

    @Override
    public void insert(UserAccount userAccount) {
        pm().makePersistent(userAccount);
    }

    @Override
    public UserAccount find(String id) {
        return pm().getObjectById(UserAccount.class, id);
    }

}
