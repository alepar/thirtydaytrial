package ru.alepar.tdt.gwt.client.action.gcal;

import ru.alepar.tdt.backend.action.core.MapTo;
import ru.alepar.tdt.backend.action.gcal.GetGoogleCalAuthUrlHandler;
import ru.alepar.tdt.gwt.client.action.core.TdtAction;
import ru.alepar.tdt.gwt.client.action.core.TdtResponse;
import ru.alepar.tdt.gwt.client.callback.GenericCallback;

/**
 * User: alepar
 * Date: Aug 9, 2010
 * Time: 12:22:23 AM
 */
@MapTo(GetGoogleCalAuthUrlHandler.class)
public class GetGoogleCalAuthUrl implements TdtAction<GetGoogleCalAuthUrl.Response> {

    private String hostPageBaseUrl;

    public String getHostPageBaseUrl() {
        return hostPageBaseUrl;
    }

    public void setHostPageBaseUrl(String hostPageBaseUrl) {
        this.hostPageBaseUrl = hostPageBaseUrl;
    }

    public static class Response implements TdtResponse {

        private String authUrl;

        public Response() { //used by serialization
        }

        public String getAuthUrl() {
            return authUrl;
        }

        public void setAuthUrl(String authUrl) {
            this.authUrl = authUrl;
        }
    }

    public GetGoogleCalAuthUrl() { //used by serialization
    }

    public abstract static class Callback extends GenericCallback<Response> {
        @Override
        public void onSuccess(Response response) {
            gotUrl(response.getAuthUrl());
        }

        public abstract void gotUrl(String authUrl);

    }
    
}
