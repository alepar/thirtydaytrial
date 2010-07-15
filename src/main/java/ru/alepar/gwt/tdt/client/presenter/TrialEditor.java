package ru.alepar.gwt.tdt.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasText;
import ru.alepar.gwt.tdt.client.event.EditTrialHistoryEvent;
import ru.alepar.gwt.tdt.client.event.TrialChangedEvent;
import ru.alepar.tdt.backend.model.Trial;

/**
 * User: alepar
 * Date: Jul 11, 2010
 * Time: 1:07:35 PM
 */
public class TrialEditor implements EditTrialHistoryEvent.Handler {

    private final HandlerManager handlerManager;
    private final Display display;
    private Trial trial;

    public interface Display {
        HasText getTitleField();
        HasClickHandlers getSaveButton();
        HasClickHandlers getCancelButton();
    }

    public TrialEditor(HandlerManager handlerManager, Display display) {
        this.handlerManager = handlerManager;
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
    public void onTrialEdit(EditTrialHistoryEvent p) {
        editTrial(p.trial);
    }

    private void doSave() {
        trial.setTitle(display.getTitleField().getText());
        handlerManager.fireEvent(new TrialChangedEvent(Trial.from(trial)));
    }

    private void doCancel() {
        updateDisplay();    
    }

    public void editTrial(Trial trial) {
        this.trial = Trial.from(trial);
        updateDisplay();
    }

    private void updateDisplay() {
        display.getTitleField().setText(this.trial.getTitle());
    }
}
