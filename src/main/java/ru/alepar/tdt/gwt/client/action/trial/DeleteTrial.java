package ru.alepar.tdt.gwt.client.action.trial;

import ru.alepar.tdt.backend.action.core.MapTo;
import ru.alepar.tdt.backend.model.user.UserTrial;
import ru.alepar.tdt.gwt.client.action.core.TdtAction;
import ru.alepar.tdt.gwt.client.action.core.TdtResponse;
import ru.alepar.tdt.gwt.client.callback.GenericCallback;

/**
 * User: alepar
 * Date: Jul 18, 2010
 * Time: 7:00:52 PM
 */

@MapTo(ru.alepar.tdt.backend.action.trial.DeleteTrialHandler.class)
public class DeleteTrial implements TdtAction<DeleteTrial.DeleteTrialResponse> {

    private UserTrial userTrial;

    @SuppressWarnings({"UnusedDeclaration"}) //used by gwt
    public DeleteTrial() {
    }

    public DeleteTrial(UserTrial userTrial) {
        this.userTrial = userTrial;
    }

    public UserTrial getUserTrial() {
        return userTrial;
    }

    public static class DeleteTrialResponse implements TdtResponse {
        private UserTrial userTrial;

        @SuppressWarnings({"UnusedDeclaration"}) //used by gwt
        public DeleteTrialResponse() {
        }

        public DeleteTrialResponse(UserTrial userTrial) {
            this.userTrial = userTrial;
        }

    }

    public static abstract class DeletedTrial extends GenericCallback<DeleteTrialResponse> {

        @Override
        public void onSuccess(DeleteTrialResponse response) {
            deletedTrial(response.userTrial);
        }

        public abstract void deletedTrial(UserTrial userTrial);

    }

}