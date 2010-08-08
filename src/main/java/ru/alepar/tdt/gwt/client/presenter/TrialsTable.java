package ru.alepar.tdt.gwt.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import ru.alepar.tdt.backend.model.trial.UserTrial;
import ru.alepar.tdt.gwt.client.event.EditUserTrialEvent;
import ru.alepar.tdt.gwt.client.event.UserTrialChangedEvent;
import ru.alepar.tdt.gwt.client.event.UserTrialDeletedEvent;
import ru.alepar.tdt.gwt.client.history.EditUserTrialHistoryEvent;

import java.util.LinkedList;

/**
 * User: alepar
 * Date: Jul 11, 2010
 * Time: 4:39:26 PM
 */
public class TrialsTable implements UserTrialChangedEvent.Handler, UserTrialDeletedEvent.Handler, EditUserTrialHistoryEvent.Handler {

    LinkedList<UserTrial> trials = new LinkedList<UserTrial>();

    private final HandlerManager eventBus;
    private final Display display;

    public interface Display {
        void updateRow(Integer row, UserTrial trial);
        void deleteRow(int row);
    }

    public TrialsTable(HandlerManager eventBus, Display display) {
        this.eventBus = eventBus;
        this.display = display;
    }

    @Override
    public void onTrialChanged(UserTrialChangedEvent p) {
        int index = 0;
        for (UserTrial trial : trials) {
            if (trial.getId().equals(p.userTrial.getId())) {
                break;
            }
            index++;
        }
        if (index == trials.size()) {
            trials.addLast(p.userTrial);
        } else {
            trials.remove(index);
            trials.add(index, p.userTrial);
        }
        display.updateRow(index, p.userTrial);
    }

    @Override
    public void onTrialDeleted(UserTrialDeletedEvent p) {
        int index = 0;
        for (UserTrial trial : trials) {
            if (trial.getId().equals(p.userTrial.getId())) {
                break;
            }
            index++;
        }
        if (index != trials.size()) {
            trials.remove(index);
            display.deleteRow(index);
        }
    }

    @Override
    public void onTrialEdit(EditUserTrialHistoryEvent p) {
        for (UserTrial trial : trials) {
            if (trial.getId().equals(p.getTrialId())) {
                eventBus.fireEvent(new EditUserTrialEvent(trial));
            }
        }
    }
}
