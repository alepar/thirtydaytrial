package ru.alepar.gwt.tdt.client.presenter;

import ru.alepar.gwt.tdt.client.event.TrialChangedEvent;
import ru.alepar.tdt.backend.model.Trial;

/**
 * User: alepar
 * Date: Jul 11, 2010
 * Time: 4:39:26 PM
 */
public class TrialsTable implements TrialChangedEvent.Handler {

    private final Display display;

    public interface Display {
        void updateTrial(Trial trial);
    }

    public TrialsTable(Display display) {
        this.display = display;
    }

    @Override
    public void onTrialChanged(TrialChangedEvent p) {
        display.updateTrial(p.trial);    
    }
}
