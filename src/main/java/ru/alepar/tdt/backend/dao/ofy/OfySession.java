package ru.alepar.tdt.backend.dao.ofy;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import ru.alepar.tdt.backend.dao.DaoSession;
import ru.alepar.tdt.backend.dao.UserAccountDao;
import ru.alepar.tdt.backend.dao.UserTrialDao;

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
        ofy = factory.begin();
    }

    @Override
    public void close() {
        assertOfyIsOpen();
        ofy = null;
    }

    private void assertOfyIsOpen() {
        if (ofy == null) {
            throw new IllegalStateException("Session is not open");
        }
    }

    @Override
    public UserTrialDao userTrial() {
        assertOfyIsOpen();
        return new UserTrialOfyDao(ofy);
    }

    @Override
    public UserAccountDao userAccount() {
        assertOfyIsOpen();
        return new UserAccountOfyDao(ofy);
    }
}
