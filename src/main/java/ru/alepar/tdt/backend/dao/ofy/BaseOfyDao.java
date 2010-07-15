package ru.alepar.tdt.backend.dao.ofy;

import com.googlecode.objectify.Objectify;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class BaseOfyDao<T> {
    protected final Objectify ofy;

    public BaseOfyDao(Objectify ofy) {
        this.ofy = ofy;
    }

    public void insert(T userAccount) {
        ofy().put(userAccount);
    }

    public Objectify ofy() {
        return ofy;
    }
}
