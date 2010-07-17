package ru.alepar.tdt.gwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.alepar.tdt.gwt.client.action.TdtAction;
import ru.alepar.tdt.gwt.client.action.TdtResponse;

@RemoteServiceRelativePath("TdtService")
public interface TdtService extends RemoteService {

    <T extends TdtResponse> T execute(TdtAction<T> action);

    /**
     * Utility/Convenience class.
     * Use TdtService.App.getInstance() to access static instance of TdtServiceAsync
     */
    public static class App {
        private static ru.alepar.tdt.gwt.client.TdtServiceAsync ourInstance = GWT.create(TdtService.class);

        public static synchronized ru.alepar.tdt.gwt.client.TdtServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
