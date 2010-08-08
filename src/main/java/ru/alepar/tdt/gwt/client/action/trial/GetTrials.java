package ru.alepar.tdt.gwt.client.action.trial;

import ru.alepar.tdt.backend.action.core.MapTo;
import ru.alepar.tdt.backend.model.trial.UserTrial;
import ru.alepar.tdt.gwt.client.action.core.TdtAction;
import ru.alepar.tdt.gwt.client.action.core.TdtResponse;
import ru.alepar.tdt.gwt.client.callback.GenericCallback;

import java.util.HashSet;

/**
 * User: alepar
 * Date: Jul 18, 2010
 * Time: 7:53:25 PM
 */

@MapTo(ru.alepar.tdt.backend.action.trial.GetTrialsHandler.class)
public class GetTrials implements TdtAction<GetTrials.Response> {

    public GetTrials() { //used by serialization
    }

    public static class Response implements TdtResponse {

        private HashSet<UserTrial> userTrials;

        @SuppressWarnings({"UnusedDeclaration"}) //used by serialization
        public Response() {

        }

        public Response(HashSet<UserTrial> userTrials) {
            this.userTrials = userTrials;
        }

        public HashSet<UserTrial> getUserTrials() {
            return userTrials;
        }

        public void setUserTrials(HashSet<UserTrial> userTrials) {
            this.userTrials = userTrials;
        }
    }

    public abstract static class GotTrials extends GenericCallback<Response> {

        @Override
        public void onSuccess(Response response) {
            gotTrials(response.getUserTrials());
        }

        public abstract void gotTrials(HashSet<UserTrial> userTrials);

    }
}
