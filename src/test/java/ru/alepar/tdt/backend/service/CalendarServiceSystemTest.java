package ru.alepar.tdt.backend.service;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.gdata.util.ServiceException;
import com.googlecode.objectify.Key;
import net.fortuna.ical4j.model.ValidationException;
import org.junit.Rule;
import org.junit.Test;
import ru.alepar.tdt.backend.dao.core.DaoSession;
import ru.alepar.tdt.backend.model.*;
import ru.alepar.tdt.testsupport.rules.LocalGae;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.Assert.*;
import static ru.alepar.tdt.backend.dao.core.DaoSessionFactoryImpl.*;

/**
 * User: red
 * Date: Jul 26, 2010
 */
public class CalendarServiceSystemTest {
    @Rule
    public LocalGae localGae = new LocalGae(new LocalDatastoreServiceTestConfig());

    @Test
    public void scheduleOneTimeEvent() {
        // performing database setup before we can go
        UserAccount userAccount
                = new UserAccount(new UserId("id"), new UserLogin("login"), new UserEmail("antonluneyv@gmail.com"));
        DaoSession session = sessionInstance();
        Key<UserAccount> userKey;
        Key<Trial> trialKey;

        try {
            session.open();
            userKey = session.userAccount().insert(userAccount);
            trialKey = session.trial().insert(new Trial("trial title", "trial content"));
        } finally {
            session.close();
        }

        CalendarService service = new CalendarService(sessionInstance());
        try {
            service.scheduleOneTimeEvent(userAccount, new UserTrial(userKey, trialKey, new TrialWhen()));
        } catch (IOException e) {
            fail(e.getMessage() + e.getStackTrace());
        } catch (ValidationException e) {
            fail(e.getMessage() + e.getStackTrace());
        } catch (URISyntaxException e) {
            fail(e.getMessage() + e.getStackTrace());
        } catch (ServiceException e) {
            fail(e.getMessage() + e.getStackTrace());
        }
    }
}
