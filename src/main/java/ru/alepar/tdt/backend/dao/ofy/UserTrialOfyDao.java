package ru.alepar.tdt.backend.dao.ofy;

import com.googlecode.objectify.Objectify;
import ru.alepar.tdt.backend.dao.UserTrialDao;
import ru.alepar.tdt.backend.model.UserTrial;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class UserTrialOfyDao extends BaseOfyDao<UserTrial> implements UserTrialDao {

    public UserTrialOfyDao(Objectify ofy) {
        super(ofy);
    }

    @Override
    public UserTrial find(String id) {
        return ofy().get(UserTrial.class, id);
    }
}
