package ru.alepar.tdt.backend.dao;

import org.junit.Rule;
import org.junit.Test;
import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.testsupport.rules.Datastore;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static ru.alepar.tdt.backend.dao.DaoSessionFactory.session;

/**
 * User: looser
 * Date: 10.07.2010
 */
public class UserAccountDaoSystemTest {
    @Rule public Datastore datastore = new Datastore();

    private static final String ID = "id";

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
