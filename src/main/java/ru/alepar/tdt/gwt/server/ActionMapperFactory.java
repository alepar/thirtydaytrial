package ru.alepar.tdt.gwt.server;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import ru.alepar.tdt.backend.dao.core.DaoSessionFactory;
import ru.alepar.tdt.backend.dao.core.DaoSessionFactoryImpl;
import ru.alepar.tdt.backend.security.ActionGuard;
import ru.alepar.tdt.backend.security.AnnotationDrivenActionGuard;

/**
 * User: alepar
 * Date: Aug 8, 2010
 */
public class ActionMapperFactory {

    private final static UserService userService = UserServiceFactory.getUserService();
    private final static DaoSessionFactory factory = new DaoSessionFactoryImpl();
    private final static ActionGuard guard = new AnnotationDrivenActionGuard();

    public static ActionMapper instance() {
        return new ActionMapper(factory, userService, guard);
    }
    
}
