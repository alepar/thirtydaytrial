package ru.alepar.tdt.backend.dao.memcached;

import ru.alepar.tdt.backend.dao.UserAccountDao;
import ru.alepar.tdt.backend.model.UserAccount;

/**
 * User: looser
 * Date: 11.07.2010
 */
public class UserAccountMemcachedDao implements UserAccountDao {
    private final UserAccountDao delegate;

    public UserAccountMemcachedDao(UserAccountDao delegate) {
        this.delegate = delegate;
    }

    @Override
    public void insert(UserAccount userAccount) {
        // invalidate cache
        delegate.insert(userAccount);
    }

    @Override
    public UserAccount find(String id) {
        // try to find in the cache
        // if not found get from delegate
        UserAccount obj = delegate.find(id);
        // put obj to cache
        return obj;
    }
}
