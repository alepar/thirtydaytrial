package ru.alepar.gwt.tdt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import ru.alepar.tdt.backend.model.Trial;

/**
 * User: alepar
 * Date: Jul 10, 2010
 * Time: 10:58:52 PM
 */
public class EditTrialHistoryEvent extends GwtEvent<EditTrialHistoryEvent.Handler> {

    public interface Handler extends EventHandler {
        public void onTrialEdit(EditTrialHistoryEvent p);
    }

    @Override
    protected void dispatch(EditTrialHistoryEvent.Handler handler) {
        handler.onTrialEdit(this);
    }

    @Override
    public Type<EditTrialHistoryEvent.Handler> getAssociatedType() {
        return TYPE;
    }

    public static final Type<EditTrialHistoryEvent.Handler> TYPE = new Type<EditTrialHistoryEvent.Handler>();

    public final Trial trial;

    public EditTrialHistoryEvent(Trial trial) {
        this.trial = trial;
    }

}