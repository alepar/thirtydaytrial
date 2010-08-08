package ru.alepar.tdt.gwt.client.action.user;

import ru.alepar.tdt.backend.action.core.MapTo;
import ru.alepar.tdt.backend.action.user.SaveGoogleDataTokenHandler;
import ru.alepar.tdt.gwt.client.action.core.TdtAction;
import ru.alepar.tdt.gwt.client.action.core.TdtVoidResponse;

/**
 * User: alepar
 * Date: Aug 8, 2010
 */
@MapTo(SaveGoogleDataTokenHandler.class)
public class SaveGoogleDataToken implements TdtAction<TdtVoidResponse> {

    private String sessionToken;

    public SaveGoogleDataToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getSessionToken() {
        return sessionToken;
    }
}
