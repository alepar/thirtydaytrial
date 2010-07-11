package ru.alepar.tdt.backend.jdo.dao;

import ru.alepar.tdt.backend.jdo.PMF;

/**
 * User: looser
 * Date: 11.07.2010
 */
public final class DaoSessionFactory {
    private DaoSessionFactory() {}

    public static DaoSession session() {
        return new JdoSession(PMF.get().getPersistenceManager());
    }
}
