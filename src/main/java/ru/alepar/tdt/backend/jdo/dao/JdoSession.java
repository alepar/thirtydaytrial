package ru.alepar.tdt.backend.jdo.dao;

import javax.jdo.PersistenceManager;

/**
 * User: looser
 * Date: 11.07.2010
 */
public class JdoSession implements DaoSession {
    private final PersistenceManager pm;

    public JdoSession(PersistenceManager pm) {
        this.pm = pm;
    }

    @Override
    public PersistenceManager pm() {
        return pm;
    }

    @Override
    public void close() {
        pm.close();
    }

    @Override
    public UserTrialDao userTrial() {
        return new UserTrialDao(this);
    }

    @Override
    public UserAccountDao userAccount() {
        return new UserAccountDao(this);
    }
}
