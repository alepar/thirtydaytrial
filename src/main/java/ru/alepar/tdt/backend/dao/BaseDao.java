package ru.alepar.tdt.backend.dao;

/**
 * User: looser
 * Date: 11.07.2010
 */
public interface BaseDao<T> {
    void insert(T userAccount);

    T find(String id);
}
