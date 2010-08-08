package ru.alepar.tdt.backend.service;

import org.junit.Test;
import ru.alepar.tdt.backend.model.trial.Trial;
import ru.alepar.tdt.backend.model.user.*;

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
