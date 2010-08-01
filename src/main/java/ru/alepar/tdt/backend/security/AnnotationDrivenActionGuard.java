package ru.alepar.tdt.backend.security;

import ru.alepar.tdt.backend.action.auth.AuthInfo;
import ru.alepar.tdt.backend.action.core.ActionHandler;

/**
 * User: alepar
 * Date: Jul 31, 2010
 * Time: 8:49:52 PM
 */
public class AnnotationDrivenActionGuard implements ActionGuard {

    @Override
    public void check(ActionHandler<?> action, AuthInfo authInfo) throws InsufficientSecurityLevelException {
        Class<? extends ActionHandler> actionClass = action.getClass();

        Allow allow = actionClass.getAnnotation(Allow.class);
        if(allow == null) {
            throw new InsufficientSecurityLevelException("no Allow annotation found");
        }
        switch (allow.value()) {
            case EVERYONE:
                return;
            case AUTHENTICATED:
                if (!authInfo.isLoggedId()) {
                    throw new InsufficientSecurityLevelException("AUTHENTICATED level or higher required");
                }
                break;
            case ADMIN:
                if (!authInfo.isAdmin()) {
                    throw new InsufficientSecurityLevelException("ADMIN level required");
                }
                break;
        }
    }

}
