package ru.alepar.gwt.tdt.server;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alepar.gwt.tdt.client.TdtService;
import ru.alepar.gwt.tdt.client.action.TdtAction;
import ru.alepar.gwt.tdt.client.action.TdtResponse;

import javax.servlet.http.HttpServletRequest;

public class TdtServiceImpl extends RemoteServiceServlet implements TdtService {

    private final Logger logger = LoggerFactory.getLogger(TdtServiceImpl.class);

    @Override
    public <T extends TdtResponse> T execute(TdtAction<T> action) {
        return action.execute();
    }
}