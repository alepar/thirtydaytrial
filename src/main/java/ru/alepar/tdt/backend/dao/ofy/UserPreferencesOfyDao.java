package ru.alepar.tdt.backend.dao.ofy;

import ru.alepar.tdt.backend.dao.UserPreferencesDao;
import ru.alepar.tdt.backend.model.user.UserPreferences;

/**
 * User: alepar
 * Date: Aug 8, 2010
 */
public class UserPreferencesOfyDao extends BaseOfyDao<UserPreferences, Long> implements UserPreferencesDao {

    public UserPreferencesOfyDao(OfySession session) {
        super(session);
    }
    
}
