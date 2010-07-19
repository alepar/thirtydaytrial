package ru.alepar.tdt.backend.action.trial;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import ru.alepar.tdt.backend.action.core.ActionHandler;
import ru.alepar.tdt.backend.dao.core.DaoSession;
import ru.alepar.tdt.backend.dao.core.DaoSessionFactory;
import ru.alepar.tdt.backend.model.Trial;
import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.backend.model.UserTrial;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * User: alepar
 * Date: Jul 18, 2010
 * Time: 7:55:53 PM
 */
public class GetTrialsHandler implements ActionHandler<GetTrialsHandler.GetTrialsResponse> {

    private final DaoSessionFactory sessionFactory;
    private final User user;

    public GetTrialsHandler(DaoSessionFactory sessionFactory, User user) {
        this.sessionFactory = sessionFactory;
        this.user = user;
    }

    @Override
    public GetTrialsResponse execute() {
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
            return new GetTrialsResponse(userTrialMap, trialMap);
        } finally {
            session.close();
        }
    }

    public static class GetTrialsResponse {

        private final HashMap<Key<UserTrial>, UserTrial> userTrials;
        private final HashMap<Key<Trial>, Trial> trials;

        public GetTrialsResponse(HashMap<Key<UserTrial>, UserTrial> userTrials, HashMap<Key<Trial>, Trial> trials) {
            this.userTrials = userTrials;
            this.trials = trials;
        }

        public HashMap<Key<UserTrial>, UserTrial> getUserTrials() {
            return userTrials;
        }

        public HashMap<Key<Trial>, Trial> getTrials() {
            return trials;
        }
    }
}
