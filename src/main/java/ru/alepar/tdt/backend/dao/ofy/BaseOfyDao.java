package ru.alepar.tdt.backend.dao.ofy;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import ru.alepar.tdt.backend.dao.core.BaseDao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public abstract class BaseOfyDao<T, K> implements BaseDao<T, K> {
    private final Objectify ofy;
    private final Class<T> typeOfT;
    private final Class<K> typeOfK;

    public BaseOfyDao(Objectify ofy) {
        this.ofy = ofy;
        Type type = this.getClass().getGenericSuperclass();
        if (!(type instanceof ParameterizedType)) {
            throw new IllegalStateException("Was expecting a parametrized type here! But got " + type);
        }

        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        //noinspection unchecked
        typeOfT = (Class<T>) actualTypeArguments[0];
        //noinspection unchecked
        typeOfK = (Class<K>) actualTypeArguments[1];
    }

    protected Objectify ofy() {
        return ofy;
    }

    @Override
    public Key<T> insert(T obj) {
        return ofy().put(obj);
    }

    @Override
    public T find(Key<T> id) {
        return ofy().get(id);
    }

    @Override
    public T find(K id) {
        if (typeOfK.equals(Long.class)) {
            return ofy().get(typeOfT, (Long) id);
        } else {
            return ofy().get(typeOfT, (String) id);
        }
    }

    @Override
    public Map<Key<T>, T> find(Iterable<Key<T>> keys) {
        return ofy().get(keys);
    }

}
