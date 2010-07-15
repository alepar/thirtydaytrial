package ru.alepar.gwt.tdt.client.history;

import com.google.gwt.event.shared.EventHandler;
import ru.alepar.tdt.backend.model.Trial;

/**
 * User: alepar
 * Date: Jul 15, 2010
 * Time: 9:45:55 PM
 */
public class EditTrialHistoryEvent extends HistoryEvent<EditTrialHistoryEvent.Handler> {

    public interface Handler extends EventHandler {
        public void onTrialEdit(EditTrialHistoryEvent p);
    }

    @Override
    public String label() {
        return "edit_trial";
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
