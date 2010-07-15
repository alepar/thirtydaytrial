package ru.alepar.tdt.testsupport.rules;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.rules.ExternalResource;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class Datastore extends ExternalResource {
    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Override
    protected void before() throws Throwable {
        helper.setUp();
    }

    @Override
    protected void after() {
        helper.tearDown();
    }
}
