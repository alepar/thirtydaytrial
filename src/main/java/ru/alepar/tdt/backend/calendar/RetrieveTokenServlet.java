package ru.alepar.tdt.backend.calendar;

import com.google.gdata.client.http.AuthSubUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alepar.tdt.gwt.client.action.user.SaveGoogleDataToken;
import ru.alepar.tdt.gwt.server.ActionMapper;
import ru.alepar.tdt.gwt.server.ActionMapperFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: alepar
 * Date: Aug 7, 2010
 * Time: 4:31:05 PM
 */
public class RetrieveTokenServlet extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(RetrieveTokenServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(request, response);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response) {
        //fetch session token
        String sessionToken;
        try {
            sessionToken = AuthSubUtil.exchangeForSessionToken(
                    AuthSubUtil.getTokenFromReply(request.getQueryString()),
                    null
            );
        } catch (Exception e) {
            throw new RuntimeException("failed to get exchangeForSessionToken", e);
        }
        //save it
        //reusing mapper allows me not to write manually fetching all that stuff (AuthInfo, Session, etc...) 
        final ActionMapper mapper = ActionMapperFactory.instance();

        try {
            mapper.map(new SaveGoogleDataToken(sessionToken)).execute();
        } catch (Exception e) {
            throw new RuntimeException("failed to save sessionToken", e);
        }
    }
    
}
