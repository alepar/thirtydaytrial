package ru.alepar.tdt.backend.security;

import ru.alepar.tdt.backend.action.auth.AuthInfo;
import ru.alepar.tdt.backend.action.core.ActionHandler;

import java.lang.annotation.Annotation;

/**
 * User: alepar
 * Date: Jul 31, 2010
 * Time: 8:49:52 PM
 */
public class AnnotationDrivenActionGuard implements ActionGuard {

    @Override
    public void check(ActionHandler<?> action, AuthInfo authInfo) throws InsufficientSecurityLevelException {
        Class<? extends ActionHandler> actionClass = action.getClass();
        Annotation[] annotations = actionClass.getDeclaredAnnotations();
        for (Annotation a : annotations) {
            if(a instanceof Allow) {
                Allow allow = (Allow) a;
                switch(allow.value()) {
                    case EVERYONE:
                        return;
                    case AUTHENTICATED:
                        if(!authInfo.isLoggedId()) {
                            throw new InsufficientSecurityLevelException();
                        }
                        break;
                    case ADMIN:
                        if(!authInfo.isAdmin()) {
                            throw new InsufficientSecurityLevelException();
                        }
                        break;
                }
            }
        }
    }

}
