package ru.alepar.tdt.gwt.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.googlecode.objectify.NotFoundException;
import ru.alepar.tdt.backend.action.core.ActionHandler;
import ru.alepar.tdt.backend.action.core.MapTo;
import ru.alepar.tdt.backend.dao.core.DaoSession;
import ru.alepar.tdt.backend.dao.core.DaoSessionFactory;
import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.backend.model.UserEmail;
import ru.alepar.tdt.backend.model.UserId;
import ru.alepar.tdt.backend.model.UserLogin;
import ru.alepar.tdt.gwt.client.action.core.TdtAction;
import ru.alepar.tdt.gwt.client.action.core.TdtResponse;

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

    public <T extends TdtResponse> ActionHandler<T> map(TdtAction<T> action) {
        MapTo to = action.getClass().getAnnotation(MapTo.class);
        String commandClassName = to.value();
        Class<ActionHandler<T>> clazz = resolveClass(commandClassName);
        Constructor<ActionHandler<T>> ctor = resolveCtor(clazz, action);
        List<Object> actualArguments = new ArrayList<Object>();
        try {
            for (Class<?> argType : ctor.getParameterTypes()) {
                if (argType.equals(DaoSessionFactory.class)) {
                    actualArguments.add(factory);
                } else if (argType.equals(User.class)) {
                    actualArguments.add(userService.getCurrentUser());
                } else if (argType.equals(action.getClass())) {
                    actualArguments.add(action);
                } else if(argType.equals(UserAccount.class)) {
                    actualArguments.add(fetchAccount(userService.getCurrentUser()));
                } else {
                    throw new RuntimeException("unknown arg type " + argType);
                }
            }
            return ctor.newInstance(actualArguments.toArray());
        } catch (Exception e) {
            throw new RuntimeException("Wasn't able to invoke ctor " + ctor + " in " + clazz + " for " + action, e);
        } 
    }

    private UserAccount fetchAccount(User user) {
        final DaoSession session = factory.session();
        session.open();
        try {
            UserAccount userAccount;
            try {
                userAccount = session.userAccount().find(user.getUserId());
            } catch (NotFoundException e) {
                userAccount = new UserAccount();
                userAccount.setId(new UserId(user.getUserId()));
                userAccount.setLogin(new UserLogin(user.getNickname()));
                userAccount.setEmail(new UserEmail(user.getEmail()));
                session.userAccount().insert(userAccount);
            }
            return userAccount;
        } finally {
            session.close();
        }
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

    @SuppressWarnings({"unchecked"})
    private synchronized <T extends TdtResponse> Class<ActionHandler<T>> resolveClass(String commandClassName) {
        Class<ActionHandler<T>> result = (Class<ActionHandler<T>>) commandClassCache.get(commandClassName);
        if (result == null) {
            try {
                result = (Class<ActionHandler<T>>) Class.forName(commandClassName);
                commandClassCache.put(commandClassName, result);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Couldn't load class for the name = " + commandClassName);
            }
        }
        return result;
    }
}
