package ru.alepar.tdt.gwt.server;

import com.google.appengine.api.users.UserService;
import ru.alepar.tdt.backend.action.auth.AuthInfo;
import ru.alepar.tdt.backend.action.core.ActionHandler;
import ru.alepar.tdt.backend.action.core.MapTo;
import ru.alepar.tdt.backend.dao.core.DaoSessionFactory;
import ru.alepar.tdt.backend.security.ActionGuard;
import ru.alepar.tdt.backend.security.InsufficientSecurityLevelException;
import ru.alepar.tdt.gwt.client.action.core.TdtAction;
import ru.alepar.tdt.gwt.client.action.core.TdtResponse;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class ActionMapper {

    private final DaoSessionFactory factory;
    private final UserService userService;
    private final ActionGuard guard;

    public ActionMapper(DaoSessionFactory factory, UserService userService, ActionGuard guard) {
        this.factory = factory;
        this.userService = userService;
        this.guard = guard;
    }

    public <T extends TdtResponse> ActionHandler<T> map(TdtAction<T> action) {
        MapTo to = action.getClass().getAnnotation(MapTo.class);
        Class<ActionHandler<T>> clazz = (Class<ActionHandler<T>>) to.value();
        Constructor<ActionHandler<T>> ctor = resolveCtor(clazz, action);
        ActionHandler<T> actionHandler = invokeCtor(action, clazz, ctor);
        try {
            guard.check(actionHandler, makeAuthInfo());
        } catch (InsufficientSecurityLevelException e) {
            throw new RuntimeException("failed to handle action " + action.getClass().getCanonicalName(), e);
        }
        return actionHandler;
    }

    private <T extends TdtResponse> ActionHandler<T> invokeCtor(TdtAction<T> action, Class<ActionHandler<T>> clazz, Constructor<ActionHandler<T>> ctor) {
        ActionHandler<T> actionHandler;
        List<Object> actualArguments = new ArrayList<Object>();
        try {
            for (Class<?> argType : ctor.getParameterTypes()) {
                if (argType.equals(DaoSessionFactory.class)) {
                    actualArguments.add(factory);
                } else if (argType.equals(AuthInfo.class)) {
                    actualArguments.add(makeAuthInfo());
                } else if (argType.equals(action.getClass())) {
                    actualArguments.add(action);
                } else {
                    throw new RuntimeException("unknown arg type " + argType);
                }
            }
            actionHandler = ctor.newInstance(actualArguments.toArray());
        } catch (Exception e) {
            throw new RuntimeException("Wasn't able to invoke ctor " + ctor + " in " + clazz + " for " + action, e);
        }
        return actionHandler;
    }

    private String getApplicationRootUrl() {
        return "/";
    }

    private AuthInfo makeAuthInfo() {
        return new AuthInfo(
                userService.createLoginURL(getApplicationRootUrl()),
                userService.createLogoutURL(getApplicationRootUrl()),
                userService.isUserLoggedIn(),
                userService.isUserLoggedIn() && userService.isUserAdmin(),
                userService.getCurrentUser()
        );
    }

    @SuppressWarnings({"unchecked"})
    private <T extends TdtResponse> Constructor<ActionHandler<T>> resolveCtor(Class<ActionHandler<T>> clazz, TdtAction<T> action) {
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            for (Class<?> aClass : constructor.getParameterTypes()) {
                if (aClass.equals(action.getClass())) {
                    return (Constructor<ActionHandler<T>>) constructor;
                }
            }
        }
        throw new RuntimeException("Wasn't able to find a matching ctor in " + clazz + " for " + action);
    }
}
