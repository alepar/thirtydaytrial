package ru.alepar.tdt.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.alepar.tdt.gwt.client.action.TdtAction;
import ru.alepar.tdt.gwt.client.action.TdtResponse;

public interface TdtServiceAsync {

    <T extends TdtResponse> void execute(TdtAction<T> action, AsyncCallback<T> async);
}
