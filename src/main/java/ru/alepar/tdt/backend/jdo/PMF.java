package ru.alepar.tdt.backend.jdo;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * User: looser
 * Date: 10.07.2010
 */
public final class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {}

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}