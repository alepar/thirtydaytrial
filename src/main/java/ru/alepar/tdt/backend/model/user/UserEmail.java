package ru.alepar.tdt.backend.model.user;

import java.io.Serializable;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class UserEmail implements Serializable {
    public final String value;

    public UserEmail(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEmail userEmail = (UserEmail) o;

        if (value != null ? !value.equals(userEmail.value) : userEmail.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserEmail{" +
                "value='" + value + '\'' +
                '}';
    }
}
