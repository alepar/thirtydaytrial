package ru.alepar.gwt.tdt.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import ru.alepar.gwt.tdt.client.action.TdtAction;
import ru.alepar.gwt.tdt.client.action.TdtResponse;
import ru.alepar.tdt.backend.dao.DaoSessionFactory;
import ru.alepar.tdt.backend.logic.Command;
import ru.alepar.tdt.backend.logic.core.MapTo;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class ActionMapper {
    private final DaoSessionFactory factory;
    private final UserService userService;
    private final Map<String, Class> commandClassCache = new HashMap<String, Class>();

    public ActionMapper(DaoSessionFactory factory, UserService userService) {
        this.factory = factory;
        this.userService = userService;
    }

    public <T extends TdtResponse> Command<T> map(TdtAction<T> action) {
        MapTo to = action.getClass().getAnnotation(MapTo.class);
        String commandClassName = to.value();
        Class<Command<T>> clazz = resolveClass(commandClassName);
        Constructor<Command<T>> ctor = resolveCtor(clazz, action);
        List<Object> actualArguments = new ArrayList<Object>();
        try {
            for (Class<?> argType : ctor.getParameterTypes()) {
                if (argType.equals(DaoSessionFactory.class)) {
                    actualArguments.add(factory);
                } else if (argType.equals(User.class)) {
                    actualArguments.add(userService.getCurrentUser());
                } else if (argType.equals(action.getClass())) {
                    actualArguments.add(action);
                } else {
                    throw new RuntimeException("unknown arg type " + argType);
                }
            }
            return ctor.newInstance(actualArguments.toArray());
        } catch (Exception e) {
            throw new RuntimeException("Wasn't able to invoke ctor " + ctor + " in " + clazz + " for " + action, e);
        } 
    }

    @SuppressWarnings({"unchecked"})
    private <T extends TdtResponse> Constructor<Command<T>> resolveCtor(Class<Command<T>> clazz, TdtAction<T> action) {
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            for (Class<?> aClass : constructor.getParameterTypes()) {
                if (aClass.equals(action.getClass())) {
                    return (Constructor<Command<T>>) constructor;
                }
            }
        }
        throw new RuntimeException("Wasn't able to find a matching ctor in " + clazz + " for " + action);
    }

    @SuppressWarnings({"unchecked"})
    private synchronized <T extends TdtResponse> Class<Command<T>> resolveClass(String commandClassName) {
        Class<Command<T>> result = (Class<Command<T>>) commandClassCache.get(commandClassName);
        if (result == null) {
            try {
                result = (Class<Command<T>>) Class.forName(commandClassName);
                commandClassCache.put(commandClassName, result);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Couldn't load class for the name = " + commandClassName);
            }
        }
        return result;
    }
}
