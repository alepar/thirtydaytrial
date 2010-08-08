package ru.alepar.tdt.backend.action.gcal;

import com.google.gdata.client.http.AuthSubUtil;
import ru.alepar.tdt.backend.action.core.ActionHandler;
import ru.alepar.tdt.backend.security.Allow;
import ru.alepar.tdt.gwt.client.action.gcal.GetGoogleCalAuthUrl;

/**
 * User: alepar
 * Date: Aug 9, 2010
 * Time: 12:31:34 AM
 */
@Allow()
public class GetGoogleCalAuthUrlHandler implements ActionHandler<GetGoogleCalAuthUrl.Response> {

    private static final String URL_FOR_GCAL_XML = "https://www.google.com/calendar/feeds/default/private/full";

    private final GetGoogleCalAuthUrl action;

    public GetGoogleCalAuthUrlHandler(GetGoogleCalAuthUrl action) {
        this.action = action;
    }

    @Override
    public GetGoogleCalAuthUrl.Response execute() {
        String requestUrl = AuthSubUtil.getRequestUrl(
                action.getHostPageBaseUrl() + "RetrieveToken",
                URL_FOR_GCAL_XML,
                false, true
        );
        GetGoogleCalAuthUrl.Response response = new GetGoogleCalAuthUrl.Response();
        response.setAuthUrl(requestUrl);
        return response;
    }

}
