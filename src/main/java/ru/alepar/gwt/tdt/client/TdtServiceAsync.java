package ru.alepar.gwt.tdt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TdtServiceAsync {
    void getMessage(String msg, AsyncCallback<String> async);
}
