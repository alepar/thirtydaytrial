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

    public GetTrialsHandler(DaoSessionFactory sessionFactory, AuthInfo authInfo) {
        this.sessionFactory = sessionFactory;
        this.user = authInfo.getUser();
    }

    @Override
    public GetTrials.GetTrialsResponse execute() {
        final DaoSession session = sessionFactory.session();
        session.open();
        try {
            UserAccount userAccount = session.userAccount().find(user.getUserId());
            Iterable<UserTrial> userTrials = session.userTrial().listUserTrials(userAccount);
            HashMap<Key<UserTrial>, UserTrial> userTrialMap = new HashMap<Key<UserTrial>, UserTrial>();
            List<Key<Trial>> trialKeys = new LinkedList<Key<Trial>>();
            for (UserTrial userTrial : userTrials) {
                trialKeys.add(userTrial.getTrial());
                userTrialMap.put(new Key<UserTrial>(UserTrial.class, userTrial.getId()), userTrial);
            }
            HashMap<Key<Trial>, Trial> trialMap = new HashMap<Key<Trial>, Trial>(session.trial().find(trialKeys));
            return new GetTrials.GetTrialsResponse(userTrialMap, trialMap);
        } finally {
            session.close();
        }
    }

}
