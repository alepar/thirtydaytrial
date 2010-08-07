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
public class CalendarServiceTest {

    @Test
    public void scheduleOneTimeEvent() {
        // performing database setup before we can go
        UserAccount userAccount = new UserAccount(new UserId("id"), new UserLogin("login"), new UserEmail("antonluneyv@gmail.com"));

        CalendarService service = new CalendarService();
        UserTrial userTrial = new UserTrial();
        Trial trial = new Trial();
        trial.setTitle("trial test title");
        trial.setContent("trial test content");
        userTrial.setTrial(trial);

        try {
            service.scheduleOneTimeEvent(userAccount, userTrial);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
