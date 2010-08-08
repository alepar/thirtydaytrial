package ru.alepar.tdt.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import ru.alepar.tdt.backend.model.user.UserTrial;

/**
 * User: alepar
 * Date: Jul 10, 2010
 * Time: 10:58:52 PM
 */
public class UserTrialChangedEvent extends GwtEvent<UserTrialChangedEvent.Handler> {

    public interface Handler extends EventHandler {
        public void onTrialChanged(UserTrialChangedEvent p);
    }

    @Override
    protected void dispatch(UserTrialChangedEvent.Handler handler) {
        handler.onTrialChanged(this);
    }

    @Override
    public GwtEvent.Type<UserTrialChangedEvent.Handler> getAssociatedType() {
        return TYPE;
    }

    public static final GwtEvent.Type<UserTrialChangedEvent.Handler> TYPE = new GwtEvent.Type<UserTrialChangedEvent.Handler>();

    public final UserTrial userTrial;

    public UserTrialChangedEvent(UserTrial userTrial) {
        this.userTrial = userTrial;
    }
}
