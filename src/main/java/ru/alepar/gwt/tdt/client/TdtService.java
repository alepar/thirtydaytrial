package ru.alepar.gwt.tdt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.alepar.gwt.tdt.client.action.TdtAction;
import ru.alepar.gwt.tdt.client.action.TdtResponse;

@RemoteServiceRelativePath("TdtService")
public interface TdtService extends RemoteService {

    <T extends TdtResponse> T execute(TdtAction<T> action);

    /**
     * Utility/Convenience class.
     * Use TdtService.App.getInstance() to access static instance of TdtServiceAsync
     */
    public static class App {
        private static TdtServiceAsync ourInstance = GWT.create(TdtService.class);

        public static synchronized TdtServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
