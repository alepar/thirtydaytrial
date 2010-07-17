package ru.alepar.tdt.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import ru.alepar.tdt.backend.model.Trial;

/**
 * User: alepar
 * Date: Jul 10, 2010
 * Time: 10:58:52 PM
 */
public class EditTrialEvent extends GwtEvent<EditTrialEvent.Handler> {

    public interface Handler extends EventHandler {
        public void onTrialEdit(EditTrialEvent p);
    }

    @Override
    protected void dispatch(EditTrialEvent.Handler handler) {
        handler.onTrialEdit(this);
    }

    @Override
    public Type<EditTrialEvent.Handler> getAssociatedType() {
        return TYPE;
    }

    public static final Type<EditTrialEvent.Handler> TYPE = new Type<EditTrialEvent.Handler>();

    public final Trial trial;

    public EditTrialEvent(Trial trial) {
        this.trial = trial;
    }
}