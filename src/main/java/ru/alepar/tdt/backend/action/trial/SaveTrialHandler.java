package ru.alepar.tdt.backend.action.trial;

import com.googlecode.objectify.Key;
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
    private final UserAccount user;

    public SaveTrialHandler(DaoSessionFactory sessionFactory, SaveTrial action, UserAccount user) {
        this.sessionFactory = sessionFactory;
        this.action = action;
        this.user = user;
    }

    @Override
    public SaveTrial.SaveTrialResponse execute() {
        final DaoSession session = sessionFactory.session();
        session.open();
        try {
            Key<UserAccount> userKey = new Key<UserAccount>(UserAccount.class, user.getId().value);
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
