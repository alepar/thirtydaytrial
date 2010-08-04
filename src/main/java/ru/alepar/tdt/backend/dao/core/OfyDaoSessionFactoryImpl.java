package ru.alepar.tdt.backend.dao.core;

import com.googlecode.objectify.ObjectifyService;
import ru.alepar.tdt.backend.dao.memcached.MemcachedSession;
import ru.alepar.tdt.backend.dao.ofy.OfySession;
import ru.alepar.tdt.backend.model.Trial;
import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.backend.model.UserTrial;

/**
 * Package visible cuz it shudn't be used in the code, pls use DaoSessionFactoryImpl instead
 *
 * User: looser
 * Date: Jul 15, 2010
 */
class OfyDaoSessionFactoryImpl implements DaoSessionFactory {

    static {
        ObjectifyService.register(Trial.class);
        ObjectifyService.register(UserTrial.class);
        ObjectifyService.register(UserAccount.class);
    }

    @Override
    public DaoSession session() {
        return sessionInstance();
    }

    public static DaoSession sessionInstance() {
        OfySession baseSession = new OfySession(ObjectifyService.factory());
        return new MemcachedSession(baseSession);
    }
}
