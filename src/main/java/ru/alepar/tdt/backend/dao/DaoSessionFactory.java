package ru.alepar.tdt.backend.dao;

import ru.alepar.tdt.backend.dao.db.PMF;
import ru.alepar.tdt.backend.dao.db.JdoSession;
import ru.alepar.tdt.backend.dao.memcached.MemcachedSession;

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
