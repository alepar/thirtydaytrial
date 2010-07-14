package ru.alepar.tdt.backend.dao.memcached;

import ru.alepar.tdt.backend.dao.DaoSession;
import ru.alepar.tdt.backend.dao.UserAccountDao;
import ru.alepar.tdt.backend.dao.UserTrialDao;

/**
 * User: looser
 * Date: 11.07.2010
 */
public class MemcachedSession implements DaoSession {
    private final DaoSession delegate;

    public MemcachedSession(DaoSession delegate) {
        this.delegate = delegate;
    }

    public void open() {
        delegate.open();
    }

    public void close() {
        delegate.close();
    }

    public UserTrialDao userTrial() {
        return delegate.userTrial();
    }

    public UserAccountDao userAccount() {
        return new UserAccountMemcachedDao(delegate.userAccount());
    }
}
