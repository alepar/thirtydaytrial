package ru.alepar.tdt.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import ru.alepar.tdt.backend.model.trial.UserTrial;

/**
 * User: alepar
 * Date: Jul 10, 2010
 * Time: 10:58:52 PM
 */
public class EditUserTrialEvent extends GwtEvent<EditUserTrialEvent.Handler> {

    public interface Handler extends EventHandler {
        public void onTrialEdit(EditUserTrialEvent p);
    }

    @Override
    protected void dispatch(EditUserTrialEvent.Handler handler) {
        handler.onTrialEdit(this);
    }

    @Override
    public Type<EditUserTrialEvent.Handler> getAssociatedType() {
        return TYPE;
    }

    public static final Type<EditUserTrialEvent.Handler> TYPE = new Type<EditUserTrialEvent.Handler>();

    public final UserTrial userTrial;

    public EditUserTrialEvent(UserTrial userTrial) {
        this.userTrial = userTrial;
    }

}