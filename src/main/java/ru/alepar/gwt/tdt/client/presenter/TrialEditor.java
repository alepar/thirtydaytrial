package ru.alepar.gwt.tdt.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasText;
import ru.alepar.gwt.tdt.client.model.Trial;

/**
 * User: alepar
 * Date: Jul 11, 2010
 * Time: 1:07:35 PM
 */
public class TrialEditor {

    private final Display display;
    private Trial trial;

    public interface Display {
        HasText getNameField();
        HasClickHandlers getSaveButton();
        HasClickHandlers getCancelButton();
    }

    public TrialEditor(Display display) {
        this.display = display;
        bindDisplay();
    }

    private void bindDisplay() {
        display.getSaveButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                doSave();
            }
        });
        display.getCancelButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                doCancel();
            }
        });
    }

    private void doSave() {
        this.trial.setName(display.getNameField().getText());
        Window.alert("saved trial, name="+this.trial.getName());
    }

    private void doCancel() {
        updateDisplay();    
    }

    public void editTrial(Trial trial) {
        this.trial = Trial.from(trial);
        updateDisplay();
    }

    private void updateDisplay() {
        display.getNameField().setText(this.trial.getName());
    }
}
