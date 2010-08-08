package ru.alepar.tdt.backend.dao;

import ru.alepar.tdt.backend.dao.core.BaseDao;
import ru.alepar.tdt.backend.model.trial.UserTrial;
import ru.alepar.tdt.backend.model.user.UserAccount;

/**
 * User: looser
 * Date: 11.07.2010
 */
public interface UserTrialDao extends BaseDao<UserTrial, Long> {

    Iterable<UserTrial> listUserTrials(UserAccount user);

}
