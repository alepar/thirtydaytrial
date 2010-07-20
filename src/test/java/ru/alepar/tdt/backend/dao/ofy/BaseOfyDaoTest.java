package ru.alepar.tdt.backend.dao.ofy;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
@RunWith(JMock.class)
public class BaseOfyDaoTest {
    Mockery ctx = new JUnit4Mockery();
    private Objectify ofy = ctx.mock(Objectify.class);
    private ObjectifyFactory factory = new ObjectifyFactory() {
        @Override
        public Objectify begin() {
            return ofy;
        }
    };
    private OfySession session = new OfySession(factory);

    @Test
    public void canFindOutCorrectClassParametrization() throws Exception {
        SomeObjDao dao = new SomeObjDao(session);

        ctx.checking(new Expectations() {{
            one(ofy).get(SomeObj.class, "id");
        }});

        session.open();
        dao.find("id");
    }
}

class SomeObj {}

class SomeObjDao extends BaseOfyDao<SomeObj, String> {

    SomeObjDao(OfySession session) {
        super(session);
    }
}
