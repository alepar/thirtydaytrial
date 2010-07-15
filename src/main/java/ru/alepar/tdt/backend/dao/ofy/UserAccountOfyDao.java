package ru.alepar.tdt.backend.dao.ofy;

import com.googlecode.objectify.Objectify;
import ru.alepar.tdt.backend.dao.UserAccountDao;
import ru.alepar.tdt.backend.model.UserAccount;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class UserAccountOfyDao extends BaseOfyDao<UserAccount> implements UserAccountDao {

    public UserAccountOfyDao(Objectify ofy) {
        super(ofy);
    }

    @Override
    public UserAccount find(String id) {
        return ofy().get(UserAccount.class, id);
    }

}
