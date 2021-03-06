package ru.alepar.tdt.gwt.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import ru.alepar.tdt.gwt.client.presenter.TrialEditor;

/**
 * User: alepar
 * Date: Jul 11, 2010
 * Time: 1:16:55 PM
 */
public class TrialEditorDisplay extends Composite implements TrialEditor.Display {
    private final Panel container;

    interface TrialEditorUiBinder extends UiBinder<Widget, TrialEditorDisplay> { }

    private static final TrialEditorUiBinder uiBinder = GWT.create(TrialEditorUiBinder.class);

    @UiField
    Label idLabel;

    @UiField
    TextBox titleField;

    @UiField
    Button saveButton;

    @UiField
    Button cancelButton;

    @UiField
    Button deleteButton;

    public TrialEditorDisplay(Panel container) {
        this.container = container;
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public HasText getIdLabel() {
        return idLabel;
    }

    @Override
    public HasText getTitleField() {
        return titleField;
    }

    @Override
    public HasClickHandlers getSaveButton() {
        return saveButton;
    }

    @Override
    public HasClickHandlers getCancelButton() {
        return cancelButton;
    }

    @Override
    public HasClickHandlers getDeleteButton() {
        return deleteButton;
    }

    @Override
    public void show() {
        container.add(this);
        titleField.setFocus(true);
    }

    @Override
    public void hide() {
        container.clear();
    }

}
