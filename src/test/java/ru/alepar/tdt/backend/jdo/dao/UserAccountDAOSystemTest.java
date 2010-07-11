package ru.alepar.tdt.backend.jdo.dao;

import org.junit.Test;
import ru.alepar.tdt.backend.jdo.PMF;
import ru.alepar.tdt.backend.jdo.model.UserAccount;

import javax.jdo.PersistenceManagerFactory;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * User: looser
 * Date: 10.07.2010
 */
public class UserAccountDAOSystemTest {
    // private final LocalServiceTestHelper helper =
    //       new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());


    @Test    
    public void canFindUserAfterInserting() {
        PersistenceManagerFactory pmf = PMF.get();

        UserAccount original = new UserAccount("id", "login", "email");
        new UserAccountDao(pmf).insert(original);

        UserAccount newOne = new UserAccountDao(pmf).find("id");

        assertThat(newOne, equalTo(original));
    }
}
