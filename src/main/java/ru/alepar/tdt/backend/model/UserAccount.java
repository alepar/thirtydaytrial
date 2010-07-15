package ru.alepar.tdt.backend.model;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * User: looser
 * Date: 10.07.2010
 */
public class UserAccount implements Serializable {
    @Id String id;

    String login;

    String email;

    @SuppressWarnings({"UnusedDeclaration"}) // used by objectify
    public UserAccount() {
    }

    public UserAccount(String id, String login, String email) {
        this.id = id;
        this.login = login;
        this.email = email;
    }

    public UserAccount(UserId id, UserLogin login, UserEmail email) {
        this.id = id.value;
        this.login = login.value;
        this.email = email.value;
    }

    public UserId getId() {
        return new UserId(id);
    }

    public void setId(UserId id) {
        this.id = id.value;
    }

    public UserLogin getLogin() {
        return new UserLogin(login);
    }

    public void setLogin(UserLogin login) {
        this.login = login.value;
    }

    public UserEmail getEmail() {
        return new UserEmail(email);
    }

    public void setEmail(UserEmail email) {
        this.email = email.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount that = (UserAccount) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
