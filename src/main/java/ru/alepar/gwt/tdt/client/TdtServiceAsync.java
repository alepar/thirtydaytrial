package ru.alepar.gwt.tdt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.alepar.gwt.tdt.client.action.TdtAction;
import ru.alepar.gwt.tdt.client.action.TdtResponse;

public interface TdtServiceAsync {

    <T extends TdtResponse> void execute(TdtAction<T> action, AsyncCallback<T> async);
}
