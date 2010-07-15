package ru.alepar.tdt.backend.dao;

import com.googlecode.objectify.ObjectifyService;
import ru.alepar.tdt.backend.dao.memcached.MemcachedSession;
import ru.alepar.tdt.backend.dao.ofy.OfySession;
import ru.alepar.tdt.backend.model.Trial;
import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.backend.model.UserTrial;

/**
 * User: looser
 * Date: 11.07.2010
 */
public final class DaoSessionFactory {
    private DaoSessionFactory() {}

    static {
        ObjectifyService.register(Trial.class);
        ObjectifyService.register(UserTrial.class);        
        ObjectifyService.register(UserAccount.class);
    }

    public static DaoSession session() {
        OfySession baseSession = new OfySession(ObjectifyService.factory());
        return new MemcachedSession(baseSession);
    }
}
