package ru.alepar.tdt.backend.action.trial;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import ru.alepar.tdt.backend.action.auth.AuthInfo;
import ru.alepar.tdt.backend.action.core.ActionHandler;
import ru.alepar.tdt.backend.dao.core.DaoSession;
import ru.alepar.tdt.backend.dao.core.DaoSessionFactory;
import ru.alepar.tdt.backend.model.Trial;
import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.backend.model.UserTrial;
import ru.alepar.tdt.backend.security.Allow;
import ru.alepar.tdt.gwt.client.action.trial.GetTrials;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import static ru.alepar.tdt.backend.security.SecurityLevel.*;

/**
 * User: alepar
 * Date: Jul 18, 2010
 * Time: 7:55:53 PM
 */
@Allow(AUTHENTICATED)
public class GetTrialsHandler implements ActionHandler<GetTrials.GetTrialsResponse> {

    private final DaoSessionFactory sessionFactory;
    private final User user;

    public GetTrialsHandler(DaoSessionFactory sessionFactory, AuthInfo authInfo, GetTrials getTrials) {
        this.sessionFactory = sessionFactory;
        this.user = authInfo.getUser();
    }

    @Override
    public GetTrials.GetTrialsResponse execute() {
        final DaoSession session = sessionFactory.session();
        session.open();
        try {
            //fetch user account
            UserAccount userAccount = session.userAccount().find(user.getUserId());
            //fetch user trials
            Iterable<UserTrial> userTrials = session.userTrial().listUserTrials(userAccount);
            List<Key<Trial>> trialKeys = new LinkedList<Key<Trial>>();
            for (UserTrial userTrial : userTrials) {
                trialKeys.add(userTrial.getTrialKey());
            }
            //fetch linked trials
            HashMap<Key<Trial>, Trial> trialMap = new HashMap<Key<Trial>, Trial>(session.trial().find(trialKeys));
            //build resultset
            HashSet<UserTrial> result = new HashSet<UserTrial>();
            for (UserTrial userTrial : userTrials) {
                userTrial.setTrial(trialMap.get(userTrial.getTrialKey()));
                result.add(userTrial);
            }
            return new GetTrials.GetTrialsResponse(result);
        } finally {
            session.close();
        }
    }

}
