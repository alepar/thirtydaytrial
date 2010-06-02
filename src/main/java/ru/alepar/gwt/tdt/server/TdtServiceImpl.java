package ru.alepar.gwt.tdt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ru.alepar.gwt.tdt.client.TdtService;

public class TdtServiceImpl extends RemoteServiceServlet implements TdtService {
    // Implementation of sample interface method
    public String getMessage(String msg) {
        return "Client said: \"" + msg + "\"<br>Server answered: \"Hi!\"";
    }
}