package ru.alepar.tdt.gwt.client.presenter;

/**
 * User: alepar
 * Date: Aug 9, 2010
 * Time: 12:14:06 AM
 */
public class GoogleDataIntegrator {

    private final Display display;

    public interface Display {
        void goToGoogle();
    }

    public GoogleDataIntegrator(Display display) {
        this.display = display;
    }

    public void go() {
        display.goToGoogle();        
    }
}
