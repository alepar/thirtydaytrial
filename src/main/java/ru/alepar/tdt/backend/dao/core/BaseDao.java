package ru.alepar.tdt.backend.dao.core;

import com.googlecode.objectify.Key;

/**
 * User: looser
 * Date: 11.07.2010
 */
public interface BaseDao<T, K> {
    Key<T> insert(T userAccount);

    T find(Key<T> id);

    T find(K id);
}
