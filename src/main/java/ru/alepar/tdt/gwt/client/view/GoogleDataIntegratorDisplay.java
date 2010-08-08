package ru.alepar.tdt.gwt.client.view;

import com.google.gwt.user.client.Window;
import ru.alepar.tdt.gwt.client.presenter.GoogleDataIntegrator;

/**
 * User: alepar
 * Date: Aug 9, 2010
 * Time: 12:16:15 AM
 */
public class GoogleDataIntegratorDisplay implements GoogleDataIntegrator.Display {

    @Override
    public void goToGoogle(String authUrl) {
        Window.Location.replace(authUrl);
    }
    
}
