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
import ru.alepar.tdt.gwt.client.action.trial.SaveTrial;

/**
 * User: alepar
 * Date: Jul 18, 2010
 * Time: 7:05:27 PM
 */
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
            Key<UserAccount> userKey = new Key<UserAccount>(UserAccount.class, user.getUserId());
            Key<Trial> trialKey = session.trial().insert(action.getTrial());
            action.getUserTrial().setUser(userKey);
            action.getUserTrial().setTrial(trialKey);
            Key<UserTrial> userTrialKey = session.userTrial().insert(action.getUserTrial());
            action.getUserTrial().setId(userTrialKey.getId());
            return new SaveTrial.SaveTrialResponse(action.getTrial(), action.getUserTrial());
        } finally {
            session.close();
        }
    }
}
