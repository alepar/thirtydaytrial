package ru.alepar.tdt.backend.action.gcal;

import ru.alepar.tdt.backend.action.auth.AuthInfo;
import ru.alepar.tdt.backend.action.core.ActionHandler;
import ru.alepar.tdt.backend.dao.core.DaoSession;
import ru.alepar.tdt.backend.dao.core.DaoSessionFactory;
import ru.alepar.tdt.backend.model.trial.Trial;
import ru.alepar.tdt.backend.model.trial.UserTrial;
import ru.alepar.tdt.backend.model.user.UserAccount;
import ru.alepar.tdt.backend.model.user.UserPreferences;
import ru.alepar.tdt.backend.security.Allow;
import ru.alepar.tdt.backend.service.CalendarService;
import ru.alepar.tdt.gwt.client.action.core.TdtVoidResponse;
import ru.alepar.tdt.gwt.client.action.gcal.ScheduleEvent;

/**
 * User: alepar
 * Date: Aug 9, 2010
 * Time: 11:09:20 PM
 */

@Allow()
public class ScheduleEventHandler implements ActionHandler<TdtVoidResponse> {

    private final DaoSessionFactory sessionFactory;
    private final AuthInfo authInfo;
    private final ScheduleEvent action;

    public ScheduleEventHandler(DaoSessionFactory sessionFactory, AuthInfo authInfo, ScheduleEvent action) {
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
            UserPreferences userPreferences = session.userPreferences().find(userAccount.getUserPreferencesKey());
            CalendarService calendarService = new CalendarService(userPreferences.getGoogleDataSessionToken());
            UserTrial userTrial = new UserTrial();
            Trial trial = new Trial();
            userTrial.setTrial(trial);
            try {
                calendarService.scheduleOneTimeEvent(userTrial);
            } catch (Exception e) {
                throw new RuntimeException("failed talking to gcal", e);
            }
            return new TdtVoidResponse();
        } finally {
            session.close();
        }
    }

}
