package ru.alepar.gwt.tdt.client.history;

import com.google.gwt.event.shared.EventHandler;

/**
 * User: alepar
 * Date: Jul 17, 2010
 * Time: 12:41:33 PM
 */
public class HomeHistoryEvent extends HistoryEvent<HomeHistoryEvent.Handler> {

    public interface Handler extends EventHandler {
        public void onHome(HomeHistoryEvent p);
    }

    public static final Type<HomeHistoryEvent.Handler> TYPE = new Type<HomeHistoryEvent.Handler>();

    @Override
    public String label() {
        return "home";
    }

    @Override
    public Type<HomeHistoryEvent.Handler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(HomeHistoryEvent.Handler handler) {
        handler.onHome(this);
    }
}
