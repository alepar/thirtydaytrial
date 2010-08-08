package ru.alepar.tdt.backend.dao.ofy;

import ru.alepar.tdt.backend.dao.UserAccountDao;
import ru.alepar.tdt.backend.model.user.UserAccount;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class UserAccountOfyDao extends BaseOfyDao<UserAccount, String> implements UserAccountDao {

    public UserAccountOfyDao(OfySession session) {
        super(session);
    }
}
