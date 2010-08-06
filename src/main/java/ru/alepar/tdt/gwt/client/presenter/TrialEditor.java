package ru.alepar.tdt.gwt.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasText;
import ru.alepar.tdt.backend.model.UserTrial;
import ru.alepar.tdt.gwt.client.TdtServiceAsync;
import ru.alepar.tdt.gwt.client.action.trial.DeleteTrial;
import ru.alepar.tdt.gwt.client.action.trial.SaveTrial;
import ru.alepar.tdt.gwt.client.event.EditUserTrialEvent;
import ru.alepar.tdt.gwt.client.event.UserTrialChangedEvent;
import ru.alepar.tdt.gwt.client.event.UserTrialDeletedEvent;
import ru.alepar.tdt.gwt.client.history.HomeHistoryEvent;

/**
 * User: alepar
 * Date: Jul 11, 2010
 * Time: 1:07:35 PM
 */
public class TrialEditor implements EditUserTrialEvent.Handler, HomeHistoryEvent.Handler {

    private final HandlerManager eventBus;
    private final Display display;
    private final TdtServiceAsync service;

    private UserTrial userTrial;

    public interface Display {
        HasText getIdLabel();
        HasText getTitleField();
        HasClickHandlers getSaveButton();
        HasClickHandlers getCancelButton();
        HasClickHandlers getDeleteButton();
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
        display.getDeleteButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                doDelete();
            }
        });
    }

    @Override
    public void onTrialEdit(EditUserTrialEvent p) {
        editTrial(p.userTrial);
    }

    @Override
    public void onHome(HomeHistoryEvent p) {
        display.hide();
    }

    private void doSave() {
        userTrial.getTrial().setTitle(display.getTitleField().getText());

        service.execute(new SaveTrial(UserTrial.from(userTrial)), new SaveTrial.SavedTrial() {

            @Override
            public void savedTrial(UserTrial userTrial) {
                eventBus.fireEvent(new UserTrialChangedEvent(userTrial));
                eventBus.fireEvent(new HomeHistoryEvent());
            }

        });
    }

    private void doCancel() {
        eventBus.fireEvent(new HomeHistoryEvent());
    }

    private void doDelete() {
        service.execute(new DeleteTrial(userTrial), new DeleteTrial.DeletedTrial() {
            @Override
            public void deletedTrial(UserTrial userTrial) {
                eventBus.fireEvent(new UserTrialDeletedEvent(userTrial));
            }
        });
        eventBus.fireEvent(new HomeHistoryEvent());
    }

    public void editTrial(UserTrial userTrial) {
        this.userTrial = UserTrial.from(userTrial);
        updateDisplay();
        display.show();
    }

    private void updateDisplay() {
        display.getIdLabel().setText(this.userTrial.getId()==null ? "" : this.userTrial.getId().toString());
        display.getTitleField().setText(this.userTrial.getTrial().getTitle());
    }
}
