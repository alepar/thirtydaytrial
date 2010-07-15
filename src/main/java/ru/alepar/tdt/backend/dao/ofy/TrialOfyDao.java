package ru.alepar.tdt.backend.dao.ofy;

import com.googlecode.objectify.Objectify;
import ru.alepar.tdt.backend.dao.TrialDao;
import ru.alepar.tdt.backend.model.Trial;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class TrialOfyDao extends BaseOfyDao<Trial, Long> implements TrialDao {
    public TrialOfyDao(Objectify ofy) {
        super(ofy);
    }
}
