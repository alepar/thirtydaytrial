package ru.alepar.tdt.backend.logic.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Requires {
    Goodies[] value();
}
