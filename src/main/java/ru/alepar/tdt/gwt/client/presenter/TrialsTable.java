package ru.alepar.tdt.gwt.client.presenter;

import com.google.gwt.event.shared.HandlerManager;
import ru.alepar.tdt.gwt.client.event.EditTrialEvent;
import ru.alepar.tdt.gwt.client.event.TrialChangedEvent;
import ru.alepar.tdt.gwt.client.history.EditTrialHistoryEvent;
import ru.alepar.tdt.backend.model.Trial;

import java.util.LinkedList;

/**
 * User: alepar
 * Date: Jul 11, 2010
 * Time: 4:39:26 PM
 */
public class TrialsTable implements TrialChangedEvent.Handler, EditTrialHistoryEvent.Handler {

    LinkedList<Trial> trials = new LinkedList<Trial>();

    private final HandlerManager eventBus;
    private final Display display;

    public interface Display {
        void updateRow(Integer row, Trial trial);
    }

    public TrialsTable(HandlerManager eventBus, Display display) {
        this.eventBus = eventBus;
        this.display = display;
    }

    @Override
    public void onTrialChanged(TrialChangedEvent p) {
        int index = 0;
        for (Trial trial : trials) {
            if(trial.getId().equals(p.trial.getId())) {
                break;
            }
            index++;
        }
        if(index == trials.size()) {
            trials.addLast(p.trial);
        } else {
            trials.remove(index);
            trials.add(index, p.trial);
        }
        display.updateRow(index, p.trial);
    }

    @Override
    public void onTrialEdit(EditTrialHistoryEvent p) {
        for (Trial trial : trials) {
            if(trial.getId().equals(p.getTrialId())) {
                eventBus.fireEvent(new EditTrialEvent(trial));
            }
        }
    }
}
