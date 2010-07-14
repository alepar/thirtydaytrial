package ru.alepar.tdt.backend.dao;

/**
 * User: looser
 * Date: 11.07.2010
 */
public interface DaoSession {
    void open();

    void close();

    UserTrialDao userTrial();

    UserAccountDao userAccount();
}
