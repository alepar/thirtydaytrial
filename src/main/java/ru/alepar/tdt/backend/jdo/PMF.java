package ru.alepar.tdt.backend.jdo;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * User: looser
 * Date: 10.07.2010
 */
public final class PMF {
    private static final PersistenceManagerFactory pmfInstance;

    static {
        Map<String, String> props = new HashMap<String, String>();
        props.put("javax.jdo.PersistenceManagerFactoryClass", "org.datanucleus.store.appengine.jdo.DatastoreJDOPersistenceManagerFactory");
        props.put("javax.jdo.option.ConnectionURL", "appengine");
        props.put("javax.jdo.option.NontransactionalRead", "true");
        props.put("javax.jdo.option.NontransactionalWrite", "true");
        props.put("javax.jdo.option.RetainValues", "true");
        props.put("datanucleus.appengine.autoCreateDatastoreTxns", "true");
        pmfInstance = JDOHelper.getPersistenceManagerFactory(props);
    }

    private PMF() {
    }

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}