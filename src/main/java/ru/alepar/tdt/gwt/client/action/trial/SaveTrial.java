package ru.alepar.tdt.gwt.client.action.trial;

import ru.alepar.tdt.backend.action.core.MapTo;
import ru.alepar.tdt.backend.model.trial.UserTrial;
import ru.alepar.tdt.gwt.client.action.core.TdtAction;
import ru.alepar.tdt.gwt.client.action.core.TdtResponse;
import ru.alepar.tdt.gwt.client.callback.GenericCallback;

/**
 * User: alepar
 * Date: Jul 18, 2010
 * Time: 7:00:52 PM
 */

@MapTo(ru.alepar.tdt.backend.action.trial.SaveTrialHandler.class)
public class SaveTrial implements TdtAction<SaveTrial.Response> {

    private UserTrial userTrial;

    @SuppressWarnings({"UnusedDeclaration"}) //used by gwt
    public SaveTrial() {
    }

    public SaveTrial(UserTrial userTrial) {
        this.userTrial = userTrial;
    }

    public UserTrial getUserTrial() {
        return userTrial;
    }

    public static class Response implements TdtResponse {
        UserTrial userTrial;

        @SuppressWarnings({"UnusedDeclaration"}) //used by gwt
        public Response() {
        }

        public Response(UserTrial userTrial) {
            this.userTrial = userTrial;
        }

        public UserTrial getUserTrial() {
            return userTrial;
        }
    }

    public static abstract class SavedTrial extends GenericCallback<Response> {

        @Override
        public void onSuccess(Response response) {
            savedTrial(response.getUserTrial());
        }

        public abstract void savedTrial(UserTrial userTrial);

    }
    
}
