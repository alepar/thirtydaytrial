package ru.alepar.gwt.tdt.client.presenter;

import com.google.gwt.user.client.ui.HasText;
import ru.alepar.gwt.tdt.client.model.Trial;

/**
 * User: alepar
 * Date: Jul 11, 2010
 * Time: 1:07:35 PM
 */
public class TrialEditor {

    private Display display;
    private Trial trial;

    public interface Display {
        HasText getNameField();
    }

    public TrialEditor(Display display) {
        this.display = display;
    }

    public void editTrial(Trial trial) {
        this.trial = Trial.from(trial);

        display.getNameField().setText(this.trial.getName());
    }
}
