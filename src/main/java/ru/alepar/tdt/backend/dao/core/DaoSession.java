package ru.alepar.tdt.backend.dao.core;

import ru.alepar.tdt.backend.dao.TrialDao;
import ru.alepar.tdt.backend.dao.UserAccountDao;
import ru.alepar.tdt.backend.dao.UserPreferencesDao;
import ru.alepar.tdt.backend.dao.UserTrialDao;

/**
 * User: looser
 * Date: 11.07.2010
 */
public interface DaoSession {
    void open();

    void close();

    TrialDao trial();

    UserTrialDao userTrial();

    UserAccountDao userAccount();

    UserPreferencesDao userPreferences();
}
