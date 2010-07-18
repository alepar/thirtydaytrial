package ru.alepar.tdt.backend.dao.ofy;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import ru.alepar.tdt.backend.dao.TrialDao;
import ru.alepar.tdt.backend.dao.UserAccountDao;
import ru.alepar.tdt.backend.dao.UserTrialDao;
import ru.alepar.tdt.backend.dao.core.DaoSession;

/**
 * User: looser
 * Date: Jul 14, 2010
 */
public class OfySession implements DaoSession {

    private final ObjectifyFactory factory;
    private Objectify ofy;

    private TrialOfyDao trialDao;
    private UserTrialOfyDao userTrialDao;
    private UserAccountOfyDao accountDao;

    public OfySession(ObjectifyFactory factory) {
        this.factory = factory;
    }

    @Override
    public void open() {
        assertOfyIsClosed();
        ofy = factory.begin();
    }

    @Override
    public void close() {
        assertOfyIsOpen();
        ofy = null;
    }

    @Override
    public TrialDao trial() {
        assertOfyIsOpen();
        if (trialDao == null) {
            trialDao = new TrialOfyDao(ofy);
        }
        return trialDao;
    }

    @Override
    public UserTrialDao userTrial() {
        assertOfyIsOpen();
        if (userTrialDao == null) {
            userTrialDao = new UserTrialOfyDao(ofy);
        }
        return userTrialDao;
    }

    @Override
    public UserAccountDao userAccount() {
        assertOfyIsOpen();
        if (accountDao == null) {
            accountDao = new UserAccountOfyDao(ofy);
        }
        return accountDao;
    }

    private void assertOfyIsOpen() {
        if (ofy == null) {
            throw new IllegalStateException("Ofy is not opened");
        }
    }

    private void assertOfyIsClosed() {
        if (ofy != null) {
            throw new IllegalStateException("Ofy is already opened");
        }
    }
}
