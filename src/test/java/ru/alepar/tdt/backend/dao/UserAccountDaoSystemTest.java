package ru.alepar.tdt.backend.dao;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import org.junit.Rule;
import org.junit.Test;
import ru.alepar.tdt.backend.dao.core.DaoSession;
import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.backend.model.UserEmail;
import ru.alepar.tdt.backend.model.UserId;
import ru.alepar.tdt.backend.model.UserLogin;
import ru.alepar.tdt.testsupport.rules.LocalGae;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static ru.alepar.tdt.backend.dao.ofy.OfyDaoSessionFactoryImpl.*;

/**
 * User: looser
 * Date: 10.07.2010
 */
public class UserAccountDaoSystemTest {
    @Rule public LocalGae localGae = new LocalGae(new LocalDatastoreServiceTestConfig()); 

    private static final UserId ID = new UserId("id");

    @Test
    public void canFindUserAfterInserting() {
        UserAccount original = new UserAccount(ID, new UserLogin("login"), new UserEmail("email"));

        DaoSession firstSession = sessionInstance();
        try {
            firstSession.open();
            firstSession.userAccount().insert(original);
        } finally {
            firstSession.close();
        }

        UserAccount newOne;
        DaoSession secondSession = sessionInstance();
        try {
            secondSession.open();
            newOne = secondSession.userAccount().find(ID.value);
        } finally {
            secondSession.close();
        }

        assertThat(newOne, equalTo(original));
    }

}
