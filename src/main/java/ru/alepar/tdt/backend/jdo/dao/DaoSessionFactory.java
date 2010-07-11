package ru.alepar.tdt.backend.jdo.dao;

import ru.alepar.tdt.backend.jdo.PMF;
import ru.alepar.tdt.backend.jdo.dao.db.JdoSession;
import ru.alepar.tdt.backend.jdo.dao.memcached.MemcachedSession;

/**
 * User: looser
 * Date: 11.07.2010
 */
public final class DaoSessionFactory {
    private DaoSessionFactory() {}

    public static DaoSession session() {
        JdoSession baseSession = new JdoSession(PMF.get());
        return new MemcachedSession(baseSession);
    }
}
