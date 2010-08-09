package ru.alepar.tdt.backend.service;

import com.google.gdata.data.DateTime;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.ServiceException;
import net.fortuna.ical4j.model.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alepar.tdt.backend.model.trial.UserTrial;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

/**
 * User: red
 * Date: Jul 25, 2010
 * Time: 1:52:37 AM
 */
public class CalendarService {

    private final com.google.gdata.client.calendar.CalendarService service;
    private final Logger log = LoggerFactory.getLogger(CalendarService.class);

    public CalendarService(String sessionToken) {
        service = new com.google.gdata.client.calendar.CalendarService("LevelUpYou-tDt-0.1");
        service.setAuthSubToken(sessionToken);
    }

    public void scheduleOneTimeEvent(UserTrial userTrial)
            throws IOException, ValidationException, URISyntaxException, ServiceException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        URL postUrl = new URL("https://www.google.com/calendar/feeds/default/private/full");
        CalendarEventEntry myEntry = new CalendarEventEntry();

        myEntry.setTitle(new PlainTextConstruct(userTrial.getTrial().getTitle()));
        myEntry.setContent(new PlainTextConstruct(userTrial.getTrial().getContent()));

        DateTime startTime = new DateTime(System.currentTimeMillis());
        DateTime endTime = new DateTime(System.currentTimeMillis() + 3600000);
        When eventTimes = new When();
        eventTimes.setStartTime(startTime);
        eventTimes.setEndTime(endTime);
        myEntry.addTime(eventTimes);

        // Send the request and receive the response
        CalendarEventEntry insertedEntry = service.insert(postUrl, myEntry);
        log.info("inserted calendarEntry: " + insertedEntry.toString());
    }

}
