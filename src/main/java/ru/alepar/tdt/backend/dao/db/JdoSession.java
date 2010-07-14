package ru.alepar.tdt.backend.dao.db;

import ru.alepar.tdt.backend.dao.DaoSession;
import ru.alepar.tdt.backend.dao.UserAccountDao;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

/**
 * User: looser
 * Date: 11.07.2010
 */
public class JdoSession implements DaoSession {
    private final PersistenceManagerFactory pmf;
    private PersistenceManager pm;

    public JdoSession(PersistenceManagerFactory pmf) {
        this.pmf = pmf;
    }

    @Override
    public void open() {
        pm = pmf.getPersistenceManager();
    }

    public PersistenceManager pm() {
        assertPmIsOpen();
        return pm;
    }

    @Override
    public void close() {
        assertPmIsOpen();
        pm.close();
    }

    @Override
    public UserTrialJdoDao userTrial() {
        return new UserTrialJdoDao(this);
    }

    @Override
    public UserAccountDao userAccount() {
        return new UserAccountJdoDao(this);
    }

    private void assertPmIsOpen() {
        if (pm == null) {
            throw new IllegalStateException("Session is not opened");
        }
    }
}
