package ru.alepar.tdt.backend.logic;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public interface Command<T> {
    T execute();
}
