package ru.alepar.gwt.tdt.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasText;
import ru.alepar.gwt.tdt.client.event.EditTrialEvent;
import ru.alepar.gwt.tdt.client.event.TrialChangedEvent;
import ru.alepar.tdt.backend.model.Trial;

/**
 * User: alepar
 * Date: Jul 11, 2010
 * Time: 1:07:35 PM
 */
public class TrialEditor implements EditTrialEvent.Handler {

    private final HandlerManager eventBus;
    private final Display display;
    private Trial trial;

    public interface Display {
        HasText getIdLabel();
        HasText getTitleField();
        HasClickHandlers getSaveButton();
        HasClickHandlers getCancelButton();
        void show();
        void hide();
    }

    public TrialEditor(HandlerManager eventBus, Display display) {
        this.eventBus = eventBus;
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

    @Override
    public void onTrialEdit(EditTrialEvent p) {
        editTrial(p.trial);
    }

    private void doSave() {
        trial.setTitle(display.getTitleField().getText());
        eventBus.fireEvent(new TrialChangedEvent(Trial.from(trial)));
        display.hide();
    }

    private void doCancel() {
        updateDisplay();
        display.hide();
    }

    public void editTrial(Trial trial) {
        this.trial = Trial.from(trial);
        updateDisplay();
        display.show();
    }

    private void updateDisplay() {
        display.getIdLabel().setText(this.trial.getId().toString());
        display.getTitleField().setText(this.trial.getTitle());
    }
}
