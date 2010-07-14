package ru.alepar.tdt.backend.dao;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.alepar.tdt.backend.model.UserAccount;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static ru.alepar.tdt.backend.dao.DaoSessionFactory.*;

/**
 * User: looser
 * Date: 10.07.2010
 */
public class UserAccountDaoSystemTest {
    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
    private static final String ID = "id";

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test 
    public void canFindUserAfterInserting() {
        UserAccount original = new UserAccount(ID, "login", "email");

        DaoSession firstSession = session();
        try {
            firstSession.open();
            firstSession.userAccount().insert(original);
        } finally {
            firstSession.close();
        }

        UserAccount newOne;
        DaoSession secondSession = session();
        try {
            secondSession.open();
            newOne = secondSession.userAccount().find(ID);
        } finally {
            secondSession.close();
        }

        assertThat(newOne, equalTo(original));
    }

}
