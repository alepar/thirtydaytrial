package ru.alepar.tdt.backend.jdo.dao;

import javax.jdo.PersistenceManager;

/**
 * User: looser
 * Date: 11.07.2010
 */
public class BaseDao {
    private final DaoSession session;

    public BaseDao(DaoSession session) {
        this.session = session;
    }

    public PersistenceManager pm() {
        return session.pm();
    }
}
