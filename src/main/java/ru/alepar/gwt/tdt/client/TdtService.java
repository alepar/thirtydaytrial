package ru.alepar.gwt.tdt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("TdtService")
public interface TdtService extends RemoteService {
    // Sample interface method of remote interface

    String getMessage(String msg);

    /**
     * Utility/Convenience class.
     * Use TdtService.App.getInstance () to access static instance of TdtServiceAsync
     */
    public static class App {
        private static TdtServiceAsync ourInstance = GWT.create(TdtService.class);

        public static synchronized TdtServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
