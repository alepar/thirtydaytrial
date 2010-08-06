package ru.alepar.tdt.gwt.client.history;

import com.google.gwt.event.shared.EventHandler;

/**
 * User: alepar
 * Date: Jul 15, 2010
 * Time: 9:45:55 PM
 */
public class EditUserTrialHistoryEvent extends HistoryEvent<EditUserTrialHistoryEvent.Handler> {

    private static final String ID_KEY = "id";

    public interface Handler extends EventHandler {
        public void onTrialEdit(EditUserTrialHistoryEvent p);
    }

    public static final Type<EditUserTrialHistoryEvent.Handler> TYPE = new Type<EditUserTrialHistoryEvent.Handler>();

    @Override
    public String label() {
        return "edit_trial";
    }

    @Override
    protected void dispatch(EditUserTrialHistoryEvent.Handler handler) {
        handler.onTrialEdit(this);
    }

    @Override
    public Type<EditUserTrialHistoryEvent.Handler> getAssociatedType() {
        return TYPE;
    }

    public Long getTrialId() {
        String id = getValue(ID_KEY);
        return id == null ? null : Long.valueOf(id);
    }

    public void setTrialId(Long trialId) {
        setParam(ID_KEY, trialId.toString());
    }
}
