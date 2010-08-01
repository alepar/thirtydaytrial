package ru.alepar.tdt.backend.security;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * User: alepar
 * Date: Jul 31, 2010
 * Time: 8:43:56 PM
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Allow {

    SecurityLevel value() default SecurityLevel.AUTHENTICATED;

}
