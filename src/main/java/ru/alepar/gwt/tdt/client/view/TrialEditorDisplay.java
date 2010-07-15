package ru.alepar.gwt.tdt.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import ru.alepar.gwt.tdt.client.presenter.TrialEditor;

/**
 * User: alepar
 * Date: Jul 11, 2010
 * Time: 1:16:55 PM
 */
public class TrialEditorDisplay extends Composite implements TrialEditor.Display {

    interface TrialEditorUiBinder extends UiBinder<Widget, TrialEditorDisplay> {}
    private static final TrialEditorUiBinder uiBinder = GWT.create(TrialEditorUiBinder.class);

    @UiField
    TextBox nameField;

    @UiField
    Button saveButton;

    @UiField
    Button cancelButton;

    public TrialEditorDisplay() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public HasText getNameField() {
        return nameField;
    }

    @Override
    public HasClickHandlers getSaveButton() {
       return saveButton;
    }

    @Override
    public HasClickHandlers getCancelButton() {
        return cancelButton;
    }
}
