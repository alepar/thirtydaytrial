package ru.alepar.gwt.tdt.server;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alepar.gwt.tdt.client.TdtService;

import javax.servlet.http.HttpServletRequest;

public class TdtServiceImpl extends RemoteServiceServlet implements TdtService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        final HttpServletRequest request = getThreadLocalRequest();
        UserService userService = UserServiceFactory.getUserService();

        String thisURL = "/";
        String answer;
        if (request.getUserPrincipal() != null) {
            answer = "<p>Hello, " +
                    request.getUserPrincipal().getName() +
                    "!  You can <a href=\"" +
                    userService.createLogoutURL(thisURL) +
                    "\">sign out</a>.</p>";
        } else {
            answer = "<p>Please <a href=\"" +
                    userService.createLoginURL(thisURL) +
                    "\">sign in</a>.</p>";
        }

        Logger logger = LoggerFactory.getLogger(TdtServiceImpl.class);
        logger.info(answer);
        return "Client said: \"" + msg + "\"<br>Server answered: " + answer;
    }
}