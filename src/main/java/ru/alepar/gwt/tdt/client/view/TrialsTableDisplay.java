package ru.alepar.gwt.tdt.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Hyperlink;
import ru.alepar.gwt.tdt.client.presenter.TrialsTable;
import ru.alepar.tdt.backend.model.Trial;

import java.util.LinkedList;

/**
 * User: alepar
 * Date: Jul 11, 2010
 * Time: 4:39:02 PM
 */
public class TrialsTableDisplay extends Composite implements TrialsTable.Display {

    FlexTable trialsTable;
    LinkedList<Long> trials = new LinkedList<Long>();

    public TrialsTableDisplay() {
        trialsTable = new FlexTable();
        initWidget(trialsTable);
    }

    @Override
    public void updateTrial(Trial trial) {
        int index = trials.indexOf(trial.getId());
        if(index == -1) {
            index = trials.size();
            trials.addLast(trial.getId());
        }
        trialsTable.setText(index, 0, trial.getId().toString());
        trialsTable.setText(index, 1, trial.getTitle());
        trialsTable.setWidget(index, 2, new Hyperlink("edit", "edit_trial"));
    }
}
