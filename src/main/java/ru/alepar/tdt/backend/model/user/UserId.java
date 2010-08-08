package ru.alepar.tdt.backend.model.user;

import java.io.Serializable;

/**
 * User: looser
 * Date: Jul 15, 2010
 */
public class UserId implements Serializable {
    public final String value;

    public UserId(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserId userId = (UserId) o;

        if (!value.equals(userId.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "UserId{" +
                "id='" + value + '\'' +
                '}';
    }
}
