package ru.alepar.gwt.tdt.client.view;

import com.google.gwt.user.client.ui.*;
import ru.alepar.gwt.tdt.client.model.Trial;
import ru.alepar.gwt.tdt.client.presenter.TrialsTable;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * User: alepar
 * Date: Jul 11, 2010
 * Time: 4:39:02 PM
 */
public class TrialsTableDisplay extends Composite implements TrialsTable.Display {

    FlexTable trialsTable;
    LinkedList<Trial> trials = new LinkedList<Trial>();

    public TrialsTableDisplay() {
        trialsTable = new FlexTable();
        initWidget(trialsTable);
    }

    @Override
    public void updateTrial(Trial trial) {
        int index = trials.indexOf(trial);
        if(index == -1) {
            index = trials.size();
            trials.addLast(trial);
        }
        trialsTable.setText(index, 0, trial.getId().toString());
        trialsTable.setText(index, 1, trial.getName());
        trialsTable.setWidget(index, 2, new Hyperlink("edit", "edit_trial"));
    }
}
