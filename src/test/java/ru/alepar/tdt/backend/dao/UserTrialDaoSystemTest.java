package ru.alepar.tdt.backend.dao;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.googlecode.objectify.Key;
import org.junit.Rule;
import org.junit.Test;
import ru.alepar.tdt.backend.dao.core.DaoSession;
import ru.alepar.tdt.backend.model.*;
import ru.alepar.tdt.testsupport.rules.LocalGae;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static ru.alepar.tdt.backend.dao.core.DaoSessionFactoryImpl.*;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class UserTrialDaoSystemTest {
    @Rule
    public LocalGae localGae = new LocalGae(new LocalDatastoreServiceTestConfig());

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

    @Test
    public void testKeyIsGeneratedAutomatically() throws Exception {
        DaoSession session = sessionInstance();
        session.open();
        try {
            Key<UserAccount> userKey = session.userAccount().insert(
                    new UserAccount(new UserId("id"), new UserLogin("login"), new UserEmail("email"))
            );

            UserTrial userTrial = new UserTrial();
            userTrial.setUser(userKey);
            session.userTrial().insert(userTrial);
            assertThat(userTrial.getId(), notNullValue());
        } finally {
            session.close();
        }
    }
}
