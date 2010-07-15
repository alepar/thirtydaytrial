package ru.alepar.tdt.backend.dao;

import com.googlecode.objectify.Key;
import org.junit.Rule;
import org.junit.Test;
import ru.alepar.tdt.backend.model.*;
import ru.alepar.tdt.testsupport.rules.TestingDatastore;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static ru.alepar.tdt.backend.dao.DaoSessionFactoryImpl.sessionInstance;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class UserTrialDaoSystemTest {
    @Rule public TestingDatastore datastore = new TestingDatastore();

    @Test
    public void canFindUserTrialAfterInserting() {
        UserTrial orig;

        DaoSession firstSession = sessionInstance();
        Key<UserTrial> userTrialKey;
        try {
            firstSession.open();
            Key<UserAccount> userKey = firstSession.userAccount().insert(
                    new UserAccount(new UserId("id"), new UserLogin("login"), new UserEmail("email"))
            );
            Key<Trial> trialKey = firstSession.trial().insert(new Trial("trial title", "trial content"));
            orig = new UserTrial(userKey, trialKey, new TrialWhen("some data"));
            userTrialKey = firstSession.userTrial().insert(orig);
        } finally {
            firstSession.close();
        }

        UserTrial newOne;
        DaoSession secondSession = sessionInstance();
        try {
            secondSession.open();
            newOne = secondSession.userTrial().find(userTrialKey);
        } finally {
            secondSession.close();
        }

        assertThat(newOne, equalTo(orig));
    }
}
