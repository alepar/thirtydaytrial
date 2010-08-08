package ru.alepar.tdt.backend.dao.ofy;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import ru.alepar.tdt.backend.dao.TrialDao;
import ru.alepar.tdt.backend.dao.UserAccountDao;
import ru.alepar.tdt.backend.dao.UserPreferencesDao;
import ru.alepar.tdt.backend.dao.UserTrialDao;
import ru.alepar.tdt.backend.dao.core.DaoSession;

/**
 * User: looser
 * Date: Jul 14, 2010
 */
public class OfySession implements DaoSession {

    private final ObjectifyFactory factory;
    private Objectify ofy;

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
        return new TrialOfyDao(this);
    }

    @Override
    public UserTrialDao userTrial() {
        return new UserTrialOfyDao(this);
    }

    @Override
    public UserAccountDao userAccount() {
        return new UserAccountOfyDao(this);
    }

    @Override
    public UserPreferencesDao userPreferences() {
        return new UserPreferencesOfyDao(this);
    }

    Objectify ofy() {
        assertOfyIsOpen();
        return ofy;
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
