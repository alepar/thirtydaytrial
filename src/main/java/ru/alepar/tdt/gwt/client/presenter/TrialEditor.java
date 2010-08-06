package ru.alepar.tdt.gwt.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasText;
import ru.alepar.tdt.backend.model.Trial;
import ru.alepar.tdt.gwt.client.TdtServiceAsync;
import ru.alepar.tdt.gwt.client.action.trial.SaveTrial;
import ru.alepar.tdt.gwt.client.event.EditTrialEvent;
import ru.alepar.tdt.gwt.client.event.TrialChangedEvent;
import ru.alepar.tdt.gwt.client.history.HomeHistoryEvent;

/**
 * User: alepar
 * Date: Jul 11, 2010
 * Time: 1:07:35 PM
 */
public class TrialEditor implements EditTrialEvent.Handler, HomeHistoryEvent.Handler {

    private final HandlerManager eventBus;
    private final Display display;
    private final TdtServiceAsync service;

    private Trial trial;

    public interface Display {
        HasText getIdLabel();
        HasText getTitleField();
        HasClickHandlers getSaveButton();
        HasClickHandlers getCancelButton();
        void show();
        void hide();
    }

    public TrialEditor(HandlerManager eventBus, Display display, TdtServiceAsync service) {
        this.eventBus = eventBus;
        this.display = display;
        this.service = service;
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

    @Override
    public void onHome(HomeHistoryEvent p) {
        display.hide();
    }

    private void doSave() {
        trial.setTitle(display.getTitleField().getText());

        service.execute(new SaveTrial(Trial.from(trial), null), new SaveTrial.SavedTrial() {
            @Override
            public void savedTrial(SaveTrial.SaveTrialResponse response) {
                eventBus.fireEvent(new TrialChangedEvent(response.getTrial()));
                eventBus.fireEvent(new HomeHistoryEvent());
            }
        });
    }

    private void doCancel() {
        updateDisplay();
        eventBus.fireEvent(new HomeHistoryEvent());
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
