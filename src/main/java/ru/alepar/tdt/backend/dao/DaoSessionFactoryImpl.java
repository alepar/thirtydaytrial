package ru.alepar.tdt.backend.dao;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class DaoSessionFactoryImpl implements DaoSessionFactory {
    @Override
    public DaoSession session() {
        return StaticDaoSessionFactory.session();
    }
}
