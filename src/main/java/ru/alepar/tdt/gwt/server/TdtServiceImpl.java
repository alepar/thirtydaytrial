package ru.alepar.tdt.gwt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alepar.tdt.backend.action.core.ActionHandler;
import ru.alepar.tdt.gwt.client.TdtService;
import ru.alepar.tdt.gwt.client.action.core.TdtAction;
import ru.alepar.tdt.gwt.client.action.core.TdtResponse;

public class TdtServiceImpl extends RemoteServiceServlet implements TdtService {

    private final static ActionMapper mapper = ActionMapperFactory.instance();

    private static Logger log = LoggerFactory.getLogger(TdtServiceImpl.class);

    @Override
    public <T extends TdtResponse> T execute(TdtAction<T> action) {
        ActionHandler<T> actionHandler = mapper.map(action);
        try {
            return actionHandler.execute();
        } catch (Exception e) {
            String message = actionHandler.getClass().getSimpleName() + " thrown exception";
            log.info(message, e);
            throw new RuntimeException(message, e);
        }
    }
}