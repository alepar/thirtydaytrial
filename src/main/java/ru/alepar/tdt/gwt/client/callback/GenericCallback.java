package ru.alepar.tdt.gwt.client.callback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * User: alepar
 * Date: Jul 10, 2010
 * Time: 11:26:02 PM
 */
public abstract class GenericCallback<T> implements AsyncCallback<T> {

    @Override
    public void onFailure(Throwable throwable) {
        Window.alert(this.getClass().getSimpleName() + " thrown exception: "+ throwable.getMessage());
        GWT.log("async call thrown exception", throwable);
    }

}
