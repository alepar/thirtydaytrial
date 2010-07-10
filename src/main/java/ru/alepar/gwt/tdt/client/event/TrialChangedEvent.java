package ru.alepar.gwt.tdt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import ru.alepar.gwt.tdt.client.model.Trial;

/**
 * User: alepar
 * Date: Jul 10, 2010
 * Time: 10:58:52 PM
 */
public class TrialChangedEvent extends GwtEvent<TrialChangedEvent.Handler> {

    public interface Handler extends EventHandler {
        public void onTrialChanged(TrialChangedEvent p);
    }

    @Override
    protected void dispatch(TrialChangedEvent.Handler handler) {
        handler.onTrialChanged(this);
    }

    @Override
    public GwtEvent.Type<TrialChangedEvent.Handler> getAssociatedType() {
        return TYPE;
    }

    public static final GwtEvent.Type<TrialChangedEvent.Handler> TYPE = new GwtEvent.Type<TrialChangedEvent.Handler>();

    public final Trial trial;

    public TrialChangedEvent(Trial trial) {
        this.trial = trial;
    }
    
}
