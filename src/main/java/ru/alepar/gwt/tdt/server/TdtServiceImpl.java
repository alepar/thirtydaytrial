package ru.alepar.gwt.tdt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alepar.gwt.tdt.client.TdtService;

public class TdtServiceImpl extends RemoteServiceServlet implements TdtService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        Logger logger = LoggerFactory.getLogger(TdtServiceImpl.class);
        logger.info("Client pinged me!");
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}