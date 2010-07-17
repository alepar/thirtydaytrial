package ru.alepar.tdt.gwt.client.history;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * User: alepar
 * Date: Jul 17, 2010
 * Time: 12:34:59 PM
 */
public class HistoryEventFactory {

    private ArrayList<HistoryEvent<?>> eventTypesList;
    private HashMap<String, HistoryEvent<?>> eventMap;

    public HistoryEventFactory() {
        eventTypesList = new ArrayList<HistoryEvent<?>>() {{
            add(new EditTrialHistoryEvent());
            add(new HomeHistoryEvent());
        }};
        prepareMap();
    }

    private void prepareMap() {
        eventMap = new HashMap<String, HistoryEvent<?>>();
        try {
            for (HistoryEvent<?> historyEvent : eventTypesList) {
                eventMap.put(historyEvent.label(), historyEvent);
            }
        } catch (Exception e) {
            throw new RuntimeException("failed to initialize HistoryEventFactory", e);
        }
    }

    public HistoryEvent<?> buildEvent(String token) {
        String label = token.split(HistoryEvent.TOKEN_SEPARATOR, 2)[0];
        HistoryEvent<?> event = eventMap.get(label);
        if(event == null) {
            throw new RuntimeException("i don't know event type with label = " + label);
        }
        event.unmarshall(token);
        return event;
    }
}
