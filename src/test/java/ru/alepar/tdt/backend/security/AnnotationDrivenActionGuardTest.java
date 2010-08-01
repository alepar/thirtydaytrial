package ru.alepar.tdt.backend.security;

import com.google.appengine.api.users.User;
import org.junit.Test;
import ru.alepar.tdt.backend.action.auth.AuthInfo;
import ru.alepar.tdt.backend.action.core.ActionHandler;

import static org.junit.Assert.*;
import static ru.alepar.tdt.backend.security.SecurityLevel.*;

/**
 * User: alepar
 * Date: Jul 31, 2010
 * Time: 8:51:13 PM
 */
public class AnnotationDrivenActionGuardTest {

    private final AuthInfo anonymousUser = new AuthInfo(null, null, false, false, null);
    private final AuthInfo loggedInUser = new AuthInfo(null, null, true, false, new User("user_email", "luu", "logged-in"));
    private final AuthInfo adminUser = new AuthInfo(null, null, true, true, new User("admin_email", "luu", "admin"));

    private final ActionHandler<?> everyoneAction = new EveryoneAction();
    private final ActionHandler<?> userAction = new AuthenticatedAction();
    private final ActionHandler<?> adminAction = new AdminAction();

    private final ActionGuard guard = new AnnotationDrivenActionGuard(); 

    @Test
    public void testAnonymousCanInvokeActionWithEveryoneAccess() {
        try {
            guard.check(everyoneAction, anonymousUser);
        } catch (InsufficientSecurityLevelException e) {
            fail();
        }
    }

    @Test
    public void testUserCanInvokeActionWithEveryoneAccess() {
        try {
            guard.check(everyoneAction, loggedInUser);
        } catch (InsufficientSecurityLevelException e) {
            fail();
        }
    }

    @Test
    public void testAdminCanInvokeActionWithEveryoneAccess() {
        try {
            guard.check(everyoneAction, adminUser);
        } catch (InsufficientSecurityLevelException e) {
            fail();
        }
    }

    @Test(expected = InsufficientSecurityLevelException.class)
    public void testEveryoneCannotInvokeActionWithUserAccess() throws InsufficientSecurityLevelException {
        guard.check(userAction, anonymousUser);
    }

    @Test
    public void testUserCanInvokeActionWithUserAccess() {
        try {
            guard.check(userAction, loggedInUser);
        } catch (InsufficientSecurityLevelException e) {
            fail();
        }
    }

    @Test
    public void testOnlyUserAndAdminCanInvokeActionWithUserAccess() {
        try {
            guard.check(userAction, adminUser);
        } catch (InsufficientSecurityLevelException e) {
            fail();
        }
    }

    @Test(expected = InsufficientSecurityLevelException.class)
    public void testEveryoneCannotInvokeActionWithAdminAccess() throws InsufficientSecurityLevelException {
        guard.check(adminAction, anonymousUser);
    }

    @Test(expected = InsufficientSecurityLevelException.class)
    public void testUserCannotInvokeActionWithAdminAccess() throws InsufficientSecurityLevelException {
        guard.check(adminAction, loggedInUser);
    }

    @Test
    public void testAdminCanInvokeActionWithAdminAccess() {
        try {
            guard.check(adminAction, adminUser);
        } catch (InsufficientSecurityLevelException e) {
            fail();
        }
    }

    @Allow(EVERYONE)
    private static class EveryoneAction implements ActionHandler {
        @Override
        public Object execute() {
            throw new RuntimeException("Not Implemented");
        }
    }

    @Allow(AUTHENTICATED)
    private static class AuthenticatedAction implements ActionHandler {
        @Override
        public Object execute() {
            throw new RuntimeException("Not Implemented");
        }
    }

    @Allow(ADMIN)
    private static class AdminAction implements ActionHandler {
        @Override
        public Object execute() {
            throw new RuntimeException("Not Implemented");
        }
    }

}
