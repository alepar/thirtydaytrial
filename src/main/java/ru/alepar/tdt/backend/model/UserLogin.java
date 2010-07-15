package ru.alepar.tdt.backend.model;

import java.io.Serializable;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class UserLogin implements Serializable {
    public final String value;

    public UserLogin(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLogin userLogin = (UserLogin) o;

        if (value != null ? !value.equals(userLogin.value) : userLogin.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "value='" + value + '\'' +
                '}';
    }
}
