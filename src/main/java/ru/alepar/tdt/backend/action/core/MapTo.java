package ru.alepar.tdt.backend.action.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MapTo {
    Class<? extends ActionHandler<?>> value();
}
