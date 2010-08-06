package ru.alepar.tdt.backend.action.trial;

import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;
import ru.alepar.tdt.backend.action.auth.AuthInfo;
import ru.alepar.tdt.backend.action.core.ActionHandler;
import ru.alepar.tdt.backend.dao.core.DaoSession;
import ru.alepar.tdt.backend.dao.core.DaoSessionFactory;
import ru.alepar.tdt.backend.model.Trial;
import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.backend.security.Allow;
import ru.alepar.tdt.gwt.client.action.trial.DeleteTrial;
import ru.alepar.tdt.gwt.client.action.trial.SaveTrial;

/**
 * User: alepar
 * Date: Aug 7, 2010
 * Time: 3:00:59 AM
 */

@Allow()
public class DeleteTrialHandler implements ActionHandler<DeleteTrial.DeleteTrialResponse> {

    private final DaoSessionFactory sessionFactory;
    private final DeleteTrial action;
    private final User user;

    public DeleteTrialHandler(DaoSessionFactory sessionFactory, DeleteTrial action, AuthInfo authInfo) {
        this.sessionFactory = sessionFactory;
        this.action = action;
        this.user = authInfo.getUser();
    }

    @Override
    public DeleteTrial.DeleteTrialResponse execute() {
        final DaoSession session = sessionFactory.session();
        session.open();
        try {
            session.userTrial().delete(action.getUserTrial());
            return new DeleteTrial.DeleteTrialResponse(action.getUserTrial());
        } finally {
            session.close();
        }
    }


}