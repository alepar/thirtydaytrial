package ru.alepar.gwt.tdt.client.view;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import ru.alepar.gwt.tdt.client.presenter.TrialsTable;

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

        // Put some text at the table's extremes.  This forces the table to be
        // 3 by 3.
        trialsTable.setText(0, 0, "upper-left corner");
        trialsTable.setText(2, 2, "bottom-right corner");

        // Let's put a button in the middle...
        trialsTable.setWidget(1, 0, new Button("Wide Button"));

        // ...and set it's column span so that it takes up the whole row.
        trialsTable.getFlexCellFormatter().setColSpan(1, 0, 3);
    }
}
