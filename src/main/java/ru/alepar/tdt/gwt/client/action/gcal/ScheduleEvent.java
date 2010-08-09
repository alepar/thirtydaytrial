package ru.alepar.tdt.gwt.client.action.gcal;

import ru.alepar.tdt.backend.action.core.MapTo;
import ru.alepar.tdt.backend.action.gcal.ScheduleEventHandler;
import ru.alepar.tdt.gwt.client.action.core.TdtAction;
import ru.alepar.tdt.gwt.client.action.core.TdtVoidResponse;
import ru.alepar.tdt.gwt.client.callback.GenericCallback;

/**
 * User: alepar
 * Date: Aug 9, 2010
 * Time: 11:06:48 PM
 */

@MapTo(ScheduleEventHandler.class)
public class ScheduleEvent implements TdtAction<TdtVoidResponse> {

    public ScheduleEvent() { //used by serialization
    }

    public abstract static class Callback extends GenericCallback<TdtVoidResponse> {
        @Override
        public void onSuccess(TdtVoidResponse tdtVoidResponse) {
            ok();
        }

        public abstract void ok();
    }
    
}
