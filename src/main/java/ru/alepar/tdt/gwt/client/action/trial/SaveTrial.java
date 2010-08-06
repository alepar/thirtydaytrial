package ru.alepar.tdt.gwt.client.action.trial;

import ru.alepar.tdt.backend.action.core.MapTo;
import ru.alepar.tdt.backend.model.Trial;
import ru.alepar.tdt.backend.model.UserTrial;
import ru.alepar.tdt.gwt.client.action.core.TdtAction;
import ru.alepar.tdt.gwt.client.action.core.TdtResponse;
import ru.alepar.tdt.gwt.client.callback.GenericCallback;

/**
 * User: alepar
 * Date: Jul 18, 2010
 * Time: 7:00:52 PM
 */

@MapTo(ru.alepar.tdt.backend.action.trial.SaveTrialHandler.class)
public class SaveTrial implements TdtAction<SaveTrial.SaveTrialResponse> {

    Trial trial;
    UserTrial userTrial;

    @SuppressWarnings({"UnusedDeclaration"}) //used by gwt
    public SaveTrial() {
    }

    public SaveTrial(Trial trial, UserTrial userTrial) {
        this.trial = trial;
        this.userTrial = userTrial;
    }

    public Trial getTrial() {
        return trial;
    }

    public UserTrial getUserTrial() {
        return userTrial;
    }

    public static class SaveTrialResponse implements TdtResponse {
        Trial trial;
        UserTrial userTrial;

        @SuppressWarnings({"UnusedDeclaration"}) //used by gwt
        public SaveTrialResponse() {
        }

        public SaveTrialResponse(Trial trial, UserTrial userTrial) {
            this.trial = trial;
            this.userTrial = userTrial;
        }

        public Trial getTrial() {
            return trial;
        }

        public UserTrial getUserTrial() {
            return userTrial;
        }
    }

    public static abstract class SavedTrial extends GenericCallback<SaveTrialResponse> {

        @Override
        public void onSuccess(SaveTrialResponse response) {
            savedTrial(response);
        }

        public abstract void savedTrial(SaveTrialResponse response);

    }
    
}
