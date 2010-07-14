package ru.alepar.tdt.backend.dao.jdo;

import ru.alepar.tdt.backend.dao.UserTrialDao;
import ru.alepar.tdt.backend.model.UserTrial;

import javax.jdo.PersistenceManager;

/**
 * User: looser
 * Date: 11.07.2010
 */
public class UserTrialJdoDao extends BaseJdoDao implements UserTrialDao {
    public UserTrialJdoDao(JdoSession session) {
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
