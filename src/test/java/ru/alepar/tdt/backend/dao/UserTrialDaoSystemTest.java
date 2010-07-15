package ru.alepar.tdt.backend.dao;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Key;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.alepar.tdt.backend.model.Trial;
import ru.alepar.tdt.backend.model.TrialWhen;
import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.backend.model.UserTrial;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static ru.alepar.tdt.backend.dao.DaoSessionFactory.session;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class UserTrialDaoSystemTest {
    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void canFindUserTrialAfterInserting() {
        UserTrial orig;

        DaoSession firstSession = session();
        Key<UserTrial> userTrialKey;
        try {
            firstSession.open();
            Key<UserAccount> userKey = firstSession.userAccount().insert(new UserAccount("id", "login", "email"));
            Key<Trial> trialKey = firstSession.trial().insert(new Trial("trial title", "trial content"));
            orig = new UserTrial(userKey, trialKey, new TrialWhen("some data"));
            userTrialKey = firstSession.userTrial().insert(orig);
        } finally {
            firstSession.close();
        }

        UserTrial newOne;
        DaoSession secondSession = session();
        try {
            secondSession.open();
            newOne = secondSession.userTrial().find(userTrialKey);
        } finally {
            secondSession.close();
        }

        assertThat(newOne, equalTo(orig));
    }
}
