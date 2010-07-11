package ru.alepar.tdt.backend.jdo.dao;

import ru.alepar.tdt.backend.jdo.model.UserTrial;

import javax.jdo.PersistenceManager;

/**
 * User: looser
 * Date: 11.07.2010
 */
public class UserTrialDao extends BaseDao {
    public UserTrialDao(DaoSession session) {
        super(session);
    }

    public void insert(UserTrial userTrial) {
        PersistenceManager pm = pm();
        pm.makePersistent(userTrial);
    }

    public UserTrial find(String id) {
        return pm().getObjectById(UserTrial.class, id);
    }
}
