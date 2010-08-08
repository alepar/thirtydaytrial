package ru.alepar.tdt.backend.action.gcal;

import ru.alepar.tdt.backend.action.auth.AuthInfo;
import ru.alepar.tdt.backend.action.core.ActionHandler;
import ru.alepar.tdt.backend.dao.core.DaoSession;
import ru.alepar.tdt.backend.dao.core.DaoSessionFactory;
import ru.alepar.tdt.backend.model.user.UserAccount;
import ru.alepar.tdt.backend.model.user.UserPreferences;
import ru.alepar.tdt.backend.security.Allow;
import ru.alepar.tdt.gwt.client.action.core.TdtVoidResponse;
import ru.alepar.tdt.gwt.client.action.gcal.SaveGoogleDataToken;

/**
 * User: alepar
 * Date: Aug 8, 2010
 */
@Allow()
public class SaveGoogleDataTokenHandler implements ActionHandler<TdtVoidResponse> {

    private final DaoSessionFactory sessionFactory;
    private final AuthInfo authInfo;
    private final SaveGoogleDataToken action;

    public SaveGoogleDataTokenHandler(DaoSessionFactory sessionFactory, AuthInfo authInfo, SaveGoogleDataToken action) {
        this.sessionFactory = sessionFactory;
        this.authInfo = authInfo;
        this.action = action;
    }

    @Override
    public TdtVoidResponse execute() {
        final DaoSession session = sessionFactory.session();
        session.open();
        try {
            UserAccount userAccount = session.userAccount().find(authInfo.getUser().getUserId());
            UserPreferences userPreferences = session.userPreferences().find(userAccount.getUserPreferencesKey());;

            userPreferences.setGoogleDataSessionToken(action.getSessionToken());
            userPreferences.setGoogleCalIntegrationEnabled(true);

            return new TdtVoidResponse();
        } finally {
            session.close();
        }
    }
    
}
