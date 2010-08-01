package ru.alepar.tdt.gwt.server;

import com.google.appengine.api.users.UserService;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.alepar.tdt.backend.action.auth.AuthInfo;
import ru.alepar.tdt.backend.action.core.ActionHandler;
import ru.alepar.tdt.backend.action.core.MapTo;
import ru.alepar.tdt.backend.dao.core.DaoSessionFactory;
import ru.alepar.tdt.backend.security.ActionGuard;
import ru.alepar.tdt.backend.security.InsufficientSecurityLevelException;
import ru.alepar.tdt.gwt.client.action.core.TdtAction;
import ru.alepar.tdt.gwt.client.action.core.TdtVoidResponse;

import static org.junit.Assert.*;

/**
 * User: alepar
 * Date: Aug 1, 2010
 * Time: 6:45:33 PM
 */

@RunWith(JMock.class)
public class ActionMapperTest {

    private final Mockery ctx = new JUnit4Mockery();

    private final DaoSessionFactory daoSessionFactory = ctx.mock(DaoSessionFactory.class);
    private final UserService userService = ctx.mock(UserService.class);
    private final ActionGuard allowAllGuard = ctx.mock(ActionGuard.class, "AllowAllActionGuard");
    private final ActionGuard denyAllGuard = ctx.mock(ActionGuard.class, "DenyAllActionGuard");

    @Before
    public void setUp() throws InsufficientSecurityLevelException {
        ctx.checking(new Expectations() {{
            allowing(userService).createLoginURL(with(any(String.class)));
                will(returnValue("login url"));
            allowing(userService).createLogoutURL(with(any(String.class)));
                will(returnValue("logout url"));
            allowing(userService).isUserLoggedIn();
                will(returnValue(false));
            allowing(userService).getCurrentUser();
                will(returnValue(null));
            allowing(allowAllGuard).check(with(any(ActionHandler.class)), with(any(AuthInfo.class)));
            allowing(denyAllGuard).check(with(any(ActionHandler.class)), with(any(AuthInfo.class)));
                will(throwException(new InsufficientSecurityLevelException()));
        }});
    }

    @Test
    public void testActionMapperRespectsActionGuardWhichAllowsEverything() {
        ActionMapper actionMapper = new ActionMapper(daoSessionFactory, userService, allowAllGuard);
        try {
            actionMapper.map(new MockAction());
        } catch (Exception e) {
            fail();
        }
    }

    @Test(expected = RuntimeException.class)
    public void testActionMapperRespectsActionGuardWhichDeniesEverything() {
        ActionMapper actionMapper = new ActionMapper(daoSessionFactory, userService, denyAllGuard);
        actionMapper.map(new MockAction());
    }

    @MapTo(MockActionHandler.class)
    public static class MockAction implements TdtAction<TdtVoidResponse> {

    }

    public static class MockActionHandler implements ActionHandler<TdtVoidResponse> {

        public MockActionHandler(MockAction ignored) {
        }

        @Override
        public TdtVoidResponse execute() {
            throw new RuntimeException("Not Implemented");
        }
    }
}
