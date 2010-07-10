package ru.alepar.gwt.tdt.server;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alepar.gwt.tdt.client.TdtService;

import javax.servlet.http.HttpServletRequest;

public class TdtServiceImpl extends RemoteServiceServlet implements TdtService {

    private final Logger logger = LoggerFactory.getLogger(TdtServiceImpl.class);

    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: Hi!";
    }
    
}