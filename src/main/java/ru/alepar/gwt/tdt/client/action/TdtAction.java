package ru.alepar.gwt.tdt.client.action;

import java.io.Serializable;

/**
 * User: alepar
 * Date: Jul 10, 2010
 * Time: 11:08:03 PM
 */
public interface TdtAction<T extends TdtResponse> extends Serializable {

    T execute();
    
}
