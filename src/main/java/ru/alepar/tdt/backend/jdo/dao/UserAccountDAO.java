package ru.alepar.tdt.backend.jdo.dao;

import ru.alepar.tdt.backend.jdo.model.UserAccount;

/**
 * User: looser
 * Date: 10.07.2010
 */
public class UserAccountDao extends BaseDao {
    public UserAccountDao(DaoSession session) {
        super(session);
    }

    public void insert(UserAccount userAccount) {
        pm().makePersistent(userAccount);
    }

    public UserAccount find(String id) {
        return pm().getObjectById(UserAccount.class, id);
    }

}
