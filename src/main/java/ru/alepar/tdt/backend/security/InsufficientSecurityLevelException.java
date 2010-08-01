package ru.alepar.tdt.backend.security;

/**
 * User: alepar
 * Date: Aug 1, 2010
 * Time: 6:12:38 PM
 */
public class InsufficientSecurityLevelException extends Exception {

    public InsufficientSecurityLevelException() {
    }

    public InsufficientSecurityLevelException(String message) {
        super(message);
    }

    public InsufficientSecurityLevelException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficientSecurityLevelException(Throwable cause) {
        super(cause);
    }
    
}
