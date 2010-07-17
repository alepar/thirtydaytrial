package ru.alepar.tdt.gwt.client.action.core;

import java.io.Serializable;

/**
 * User: alepar
 * Date: Jul 10, 2010
 * Time: 11:08:03 PM
 */
public interface TdtAction<T extends TdtResponse> extends Serializable {

    T execute();
    
}
