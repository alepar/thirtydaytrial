package ru.alepar.tdt.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import ru.alepar.tdt.backend.model.UserTrial;

/**
 * User: alepar
 * Date: Jul 10, 2010
 * Time: 10:58:52 PM
 */
public class UserTrialDeletedEvent extends GwtEvent<UserTrialDeletedEvent.Handler> {

    public interface Handler extends EventHandler {
        public void onTrialDeleted(UserTrialDeletedEvent p);
    }

    @Override
    protected void dispatch(UserTrialDeletedEvent.Handler handler) {
        handler.onTrialDeleted(this);
    }

    @Override
    public Type<UserTrialDeletedEvent.Handler> getAssociatedType() {
        return TYPE;
    }

    public static final Type<UserTrialDeletedEvent.Handler> TYPE = new Type<UserTrialDeletedEvent.Handler>();

    public final UserTrial userTrial;

    public UserTrialDeletedEvent(UserTrial userTrial) {
        this.userTrial = userTrial;
    }
}