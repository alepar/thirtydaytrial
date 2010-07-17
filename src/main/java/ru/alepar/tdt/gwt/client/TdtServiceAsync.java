package ru.alepar.tdt.gwt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.alepar.tdt.gwt.client.action.core.TdtAction;
import ru.alepar.tdt.gwt.client.action.core.TdtResponse;

public interface TdtServiceAsync {

    <T extends TdtResponse> void execute(TdtAction<T> action, AsyncCallback<T> async);
}
