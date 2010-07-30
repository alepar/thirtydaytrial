package ru.alepar.tdt.backend.service;

import com.google.gdata.data.DateTime;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.ServiceException;
import net.fortuna.ical4j.model.ValidationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import ru.alepar.tdt.backend.dao.core.DaoSession;
import ru.alepar.tdt.backend.model.Trial;
import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.backend.model.UserTrial;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * User: red
 * Date: Jul 25, 2010
 * Time: 1:52:37 AM
 */
public class CalendarService {
    private Log log = LogFactory.getLog(CalendarService.class);
    private DaoSession session;

    public CalendarService(DaoSession session) {
        this.session = session;
    }

    public void scheduleOneTimeEvent(UserAccount userAccount, UserTrial userTrial)
            throws IOException, ValidationException, URISyntaxException, ServiceException {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(new java.util.Date());

        // initialise our trial event
        Trial trial = null;
        session.open();
        try {
            trial = session.trial().find(userTrial.getTrial());
        } finally {
            session.close();
        }

        URL postUrl = new URL("https://www.google.com/calendar/feeds/"
                + userAccount.getEmail().value
                + "/private/full");
        CalendarEventEntry myEntry = new CalendarEventEntry();

        myEntry.setTitle(new PlainTextConstruct(trial.getTitle()));
        myEntry.setContent(new PlainTextConstruct(trial.getContent()));

        DateTime startTime = new DateTime(System.currentTimeMillis());
        DateTime endTime = new DateTime(System.currentTimeMillis() + 3600000);
        When eventTimes = new When();
        eventTimes.setStartTime(startTime);
        eventTimes.setEndTime(endTime);
        myEntry.addTime(eventTimes);

        // Send the request and receive the response
        com.google.gdata.client.calendar.CalendarService service = new com.google.gdata.client.calendar.CalendarService("LevelUpYou-tDt-0.1");
        service.setUserCredentials(userAccount.getEmail().value, "password");
        CalendarEventEntry insertedEntry = service.insert(postUrl, myEntry);
        System.out.println(insertedEntry);
    }
}
