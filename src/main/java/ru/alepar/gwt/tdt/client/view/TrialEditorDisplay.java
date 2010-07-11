package ru.alepar.gwt.tdt.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import ru.alepar.gwt.tdt.client.presenter.TrialEditor;

/**
 * User: alepar
 * Date: Jul 11, 2010
 * Time: 1:16:55 PM
 */
public class TrialEditorDisplay extends Composite implements TrialEditor.Display {

    interface TrialEditorUiBinder extends UiBinder<Widget, TrialEditorDisplay> {}
    private static TrialEditorUiBinder uiBinder = GWT.create(TrialEditorUiBinder.class);

    @UiField
    TextBox nameField;

    public TrialEditorDisplay() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public HasText getNameField() {
        return nameField;
    }
}
