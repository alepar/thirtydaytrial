package ru.alepar.tdt.gwt.client.presenter;

import com.google.gwt.core.client.GWT;
import ru.alepar.tdt.gwt.client.TdtServiceAsync;
import ru.alepar.tdt.gwt.client.action.gcal.GetGoogleCalAuthUrl;

/**
 * User: alepar
 * Date: Aug 9, 2010
 * Time: 12:14:06 AM
 */
public class GoogleDataIntegrator {

    private final Display display;
    private final TdtServiceAsync service;

    public interface Display {
        void goToGoogle(String authUrl);
    }

    public GoogleDataIntegrator(Display display, TdtServiceAsync service) {
        this.display = display;
        this.service = service;
    }

    public void go() {
        GetGoogleCalAuthUrl calAuthUrl = new GetGoogleCalAuthUrl();
        calAuthUrl.setHostPageBaseUrl(GWT.getHostPageBaseURL());
        service.execute(calAuthUrl, new GetGoogleCalAuthUrl.Callback() {
            @Override
            public void gotUrl(String authUrl) {
                display.goToGoogle(authUrl);
            }
        });
    }
}
