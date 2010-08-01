package ru.alepar.tdt.backend.security;

import ru.alepar.tdt.backend.action.auth.AuthInfo;
import ru.alepar.tdt.backend.action.core.ActionHandler;

/**
 * User: alepar
 * Date: Jul 31, 2010
 * Time: 8:47:13 PM
 */
public interface ActionGuard {

    void check(ActionHandler<?> action, AuthInfo authInfo) throws InsufficientSecurityLevelException;

}
