package ru.alepar.tdt.backend.action.trial;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import ru.alepar.tdt.backend.action.auth.AuthInfo;
import ru.alepar.tdt.backend.action.core.ActionHandler;
import ru.alepar.tdt.backend.dao.core.DaoSession;
import ru.alepar.tdt.backend.dao.core.DaoSessionFactory;
import ru.alepar.tdt.backend.model.trial.Trial;
import ru.alepar.tdt.backend.model.user.UserAccount;
import ru.alepar.tdt.backend.security.Allow;
import ru.alepar.tdt.gwt.client.action.trial.SaveTrial;

import static ru.alepar.tdt.backend.security.SecurityLevel.*;

/**
 * User: alepar
 * Date: Jul 18, 2010
 * Time: 7:05:27 PM
 */
@Allow(AUTHENTICATED)
public class SaveTrialHandler implements ActionHandler<SaveTrial.SaveTrialResponse> {

    private final DaoSessionFactory sessionFactory;
    private final SaveTrial action;
    private final User user;

    public SaveTrialHandler(DaoSessionFactory sessionFactory, SaveTrial action, AuthInfo authInfo) {
        this.sessionFactory = sessionFactory;
        this.action = action;
        this.user = authInfo.getUser();
    }

    @Override
    public SaveTrial.SaveTrialResponse execute() {
        final DaoSession session = sessionFactory.session();
        session.open();
        try {
            Key<Trial> trialKey = session.trial().insert(action.getUserTrial().getTrial());

            Key<UserAccount> userKey = new Key<UserAccount>(UserAccount.class, user.getUserId());
            action.getUserTrial().setUserKey(userKey);
            action.getUserTrial().setTrialKey(trialKey);
            session.userTrial().insert(action.getUserTrial());

            return new SaveTrial.SaveTrialResponse(action.getUserTrial());
        } finally {
            session.close();
        }
    }
}
