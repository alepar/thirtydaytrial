package ru.alepar.tdt.backend.dao.core;

import com.googlecode.objectify.Key;

import java.util.Map;

/**
 * User: looser
 * Date: 11.07.2010
 */
public interface BaseDao<T, K> {
    Key<T> insert(T obj);

    void delete(T obj);

    void delete(Key<T> key);

    T find(Key<T> id);

    T find(K id);

    Map<Key<T>, T> find(Iterable<Key<T>> keys);
}
