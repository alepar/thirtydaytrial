package ru.alepar.gwt.tdt.client.history;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;

/**
 * User: alepar
 * Date: Jul 17, 2010
 * Time: 2:18:44 PM
 */
public class HistoryAwareHandlerManager extends HandlerManager {

    public HistoryAwareHandlerManager(Object source, boolean fireInReverseOrder) {
        super(source, fireInReverseOrder);
    }

    public HistoryAwareHandlerManager(Object source) {
        super(source);
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
        if(event instanceof HistoryEvent) {
            HistoryEvent<?> historyEvent = (HistoryEvent<?>) event;
            History.newItem(historyEvent.token());
        }
        super.fireEvent(event);
    }
}
