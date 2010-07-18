package ru.alepar.tdt.backend.dao.ofy;

import com.googlecode.objectify.ObjectifyService;
import ru.alepar.tdt.backend.dao.core.DaoSession;
import ru.alepar.tdt.backend.dao.core.DaoSessionFactory;
import ru.alepar.tdt.backend.dao.memcached.MemcachedSession;
import ru.alepar.tdt.backend.model.Trial;
import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.backend.model.UserTrial;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class OfyDaoSessionFactoryImpl implements DaoSessionFactory {

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