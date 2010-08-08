package ru.alepar.tdt.backend.action.gcal;

import com.google.gdata.client.http.AuthSubUtil;
import ru.alepar.tdt.backend.action.core.ActionHandler;
import ru.alepar.tdt.gwt.client.action.gcal.GetGoogleCalAuthUrl;

/**
 * User: alepar
 * Date: Aug 9, 2010
 * Time: 12:31:34 AM
 */
public class GetGoogleCalAuthUrlHandler implements ActionHandler<GetGoogleCalAuthUrl.Response> {

    @Override
    public GetGoogleCalAuthUrl.Response execute() {
        String requestUrl = AuthSubUtil.getRequestUrl(
                "http://localhost:8080/RetrieveTokenServlet",
                "https://www.google.com/calendar/feeds/default/private/full",
                false, true
        );
        GetGoogleCalAuthUrl.Response response = new GetGoogleCalAuthUrl.Response();
        response.setAuthUrl(requestUrl);
        return response;
    }

}
