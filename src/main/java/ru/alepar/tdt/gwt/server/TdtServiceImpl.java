package ru.alepar.tdt.gwt.server;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alepar.tdt.gwt.client.TdtService;
import ru.alepar.tdt.gwt.client.action.TdtAction;
import ru.alepar.tdt.gwt.client.action.TdtResponse;
import ru.alepar.tdt.backend.dao.DaoSessionFactory;
import ru.alepar.tdt.backend.dao.DaoSessionFactoryImpl;

public class TdtServiceImpl extends RemoteServiceServlet implements TdtService {
    private final UserService userService = UserServiceFactory.getUserService();
    private final DaoSessionFactory factory = new DaoSessionFactoryImpl();
    private final ActionMapper mapper = new ActionMapper(factory, userService);
    private final Logger logger = LoggerFactory.getLogger(TdtServiceImpl.class);

    @Override
    public <T extends TdtResponse> T execute(TdtAction<T> action) {
        return mapper.map(action).execute();
    }
}