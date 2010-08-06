package ru.alepar.tdt.gwt.server;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alepar.tdt.backend.action.core.ActionHandler;
import ru.alepar.tdt.backend.dao.core.DaoSessionFactory;
import ru.alepar.tdt.backend.dao.core.DaoSessionFactoryImpl;
import ru.alepar.tdt.backend.security.ActionGuard;
import ru.alepar.tdt.backend.security.AnnotationDrivenActionGuard;
import ru.alepar.tdt.gwt.client.TdtService;
import ru.alepar.tdt.gwt.client.action.core.TdtAction;
import ru.alepar.tdt.gwt.client.action.core.TdtResponse;

public class TdtServiceImpl extends RemoteServiceServlet implements TdtService {

    private final static UserService userService = UserServiceFactory.getUserService();
    private final static DaoSessionFactory factory = new DaoSessionFactoryImpl();
    private final static ActionGuard guard = new AnnotationDrivenActionGuard();
    private final static ActionMapper mapper = new ActionMapper(factory, userService, guard);

    private final static Logger logger = LoggerFactory.getLogger(TdtServiceImpl.class);

    @Override
    public <T extends TdtResponse> T execute(TdtAction<T> action) {
        ActionHandler<T> actionHandler = mapper.map(action);
        try {
            return actionHandler.execute();
        } catch (Exception e) {
            String message = actionHandler.getClass().getSimpleName() + " thrown exception";
            logger.info(message, e);
            throw new RuntimeException(message, e);
        }
    }
}