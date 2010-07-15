package ru.alepar.tdt.testsupport.rules;

import com.google.appengine.tools.development.testing.LocalServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.rules.ExternalResource;

import java.util.Collections;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class LocalGae extends ExternalResource {
    public static final String MY_USER_ID = "my_user_id";

    public final LocalServiceTestHelper helper;

    public LocalGae(LocalServiceTestConfig... configs) {
        helper = new LocalServiceTestHelper(configs)
                .setEnvAttributes(Collections.singletonMap("com.google.appengine.api.users.UserService.user_id_key", (Object) MY_USER_ID))
                .setEnvEmail("my@email.com")
                .setEnvIsLoggedIn(true)
                .setEnvAuthDomain("email.com");
    }

    @Override
    protected void before() throws Throwable {
        helper.setUp();
    }

    @Override
    protected void after() {
        helper.tearDown();
    }
}
