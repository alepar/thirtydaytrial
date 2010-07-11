package ru.alepar.tdt.backend.jdo.dao.db;

import javax.jdo.PersistenceManager;

/**
 * User: looser
 * Date: 11.07.2010
 */
public class BaseJdoDao {
    private final JdoSession session;

    public BaseJdoDao(JdoSession session) {
        this.session = session;
    }

    public PersistenceManager pm() {
        return session.pm();
    }
}
