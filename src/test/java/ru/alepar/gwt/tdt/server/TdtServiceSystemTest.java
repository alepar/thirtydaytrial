package ru.alepar.gwt.tdt.server;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalUserServiceTestConfig;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import ru.alepar.gwt.tdt.client.action.user.AddUserAction;
import ru.alepar.tdt.backend.dao.DaoSession;
import ru.alepar.tdt.backend.dao.DaoSessionFactoryImpl;
import ru.alepar.tdt.backend.model.UserAccount;
import ru.alepar.tdt.backend.model.UserLogin;
import ru.alepar.tdt.testsupport.rules.LocalGae;

import static org.junit.Assert.assertThat;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class TdtServiceSystemTest {
    @Rule
    public LocalGae localGae = new LocalGae(
            new LocalUserServiceTestConfig(),
            new LocalDatastoreServiceTestConfig()
    );

    public static final UserLogin LOGIN = new UserLogin("user_login");

    @Test
    public void commandsAreConvertedAndResultIsStoredInTheDatabase() {
        User user = UserServiceFactory.getUserService().getCurrentUser();
        assertThat(user.getUserId(), Matchers.equalTo(LocalGae.MY_USER_ID));

        new TdtServiceImpl().execute(new AddUserAction(LOGIN));

        DaoSession session = DaoSessionFactoryImpl.sessionInstance();
        session.open();
        try {
            UserAccount userAccount = session.userAccount().find(LocalGae.MY_USER_ID);
            assertThat(userAccount.getLogin(), Matchers.equalTo(LOGIN));
        } finally {
            session.close();
        }
    }


}
