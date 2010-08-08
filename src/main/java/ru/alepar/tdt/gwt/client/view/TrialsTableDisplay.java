package ru.alepar.tdt.gwt.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Hyperlink;
import ru.alepar.tdt.backend.model.user.UserTrial;
import ru.alepar.tdt.gwt.client.history.EditUserTrialHistoryEvent;
import ru.alepar.tdt.gwt.client.presenter.TrialsTable;

/**
 * User: alepar
 * Date: Jul 11, 2010
 * Time: 4:39:02 PM
 */
public class TrialsTableDisplay extends Composite implements TrialsTable.Display {

    FlexTable trialsTable;

    public TrialsTableDisplay() {
        trialsTable = new FlexTable();
        initWidget(trialsTable);
    }

    @Override
    public void updateRow(Integer row, UserTrial trial) {
        trialsTable.setText(row, 0, trial.getId().toString());
        trialsTable.setText(row, 1, trial.getTrial().getTitle());
        EditUserTrialHistoryEvent event = new EditUserTrialHistoryEvent();
        event.setTrialId(trial.getId());
        trialsTable.setWidget(row, 2, new Hyperlink("edit", event.token()));
    }

    @Override
    public void deleteRow(int row) {
        trialsTable.removeRow(row);
    }
}
