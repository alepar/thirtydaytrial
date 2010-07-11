package ru.alepar.tdt.backend.jdo.dao;

import javax.jdo.PersistenceManager;

/**
 * User: looser
 * Date: 11.07.2010
 */
public interface DaoSession {
    PersistenceManager pm();

    UserTrialDao userTrial();

    UserAccountDao userAccount();
}
