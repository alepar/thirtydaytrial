package ru.alepar.tdt.backend.dao.ofy;

import com.googlecode.objectify.Objectify;
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

    @Test
    public void canFindOutCorrectClassParametrization() throws Exception {
        SomeObjDao dao = new SomeObjDao(ofy);

        ctx.checking(new Expectations() {{
            one(ofy).get(SomeObj.class, "id");
        }});
        
        dao.find("id");
    }
}

class SomeObj {}

class SomeObjDao extends BaseOfyDao<SomeObj, String> {

    public SomeObjDao(Objectify ofy) {
        super(ofy);
    }
}
