package ru.alepar.tdt.gwt.client.action.trial;

import com.googlecode.objectify.Key;
import ru.alepar.tdt.backend.action.core.MapTo;
import ru.alepar.tdt.backend.model.Trial;
import ru.alepar.tdt.backend.model.UserTrial;
import ru.alepar.tdt.gwt.client.action.core.TdtAction;
import ru.alepar.tdt.gwt.client.action.core.TdtVoidRequest;

import java.util.HashMap;

/**
 * User: alepar
 * Date: Jul 18, 2010
 * Time: 7:53:25 PM
 */

@MapTo(ru.alepar.tdt.backend.action.trial.GetTrialsHandler.class)
public class GetTrials implements TdtAction<TdtVoidRequest> {
    public static class GetTrialsResponse {

        private HashMap<Key<UserTrial>, UserTrial> userTrials;
        private HashMap<Key<Trial>, Trial> trials;

        @SuppressWarnings({"UnusedDeclaration"}) //used by gwt
        public GetTrialsResponse() {

        }

        public GetTrialsResponse(HashMap<Key<UserTrial>, UserTrial> userTrials, HashMap<Key<Trial>, Trial> trials) {
            this.userTrials = userTrials;
            this.trials = trials;
        }

        public HashMap<Key<UserTrial>, UserTrial> getUserTrials() {
            return userTrials;
        }

        public HashMap<Key<Trial>, Trial> getTrials() {
            return trials;
        }
    }
}
