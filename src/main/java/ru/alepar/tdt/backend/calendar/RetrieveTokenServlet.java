package ru.alepar.tdt.backend.calendar;

import com.google.gdata.client.http.AuthSubUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        String onetimeUseToken = AuthSubUtil.getTokenFromReply(request.getQueryString());
        String sessionToken = AuthSubUtil.exchangeForSessionToken(onetimeUseToken, null); 
    }
}
