package ru.alepar.tdt.backend.dao.core;

/**
 * User: looser
 * Date: 19.07.2010
 */
public class DaoSessionFactoryImpl implements DaoSessionFactory {

    @Override
    public DaoSession session() {
        return sessionInstance();
    }

    public static DaoSession sessionInstance() {
        return OfyDaoSessionFactoryImpl.sessionInstance();
    }
}
