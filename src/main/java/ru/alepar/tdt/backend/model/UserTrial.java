package ru.alepar.tdt.backend.model;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;

import javax.persistence.Embedded;
import javax.persistence.Id;


/**
 * User: looser
 * Date: 11.07.2010
 */
public class UserTrial {
    @Id Long id;

    @Parent Key<UserAccount> user;

    Key<Trial> trial;

    @Embedded TrialWhen when;

    @SuppressWarnings({"UnusedDeclaration"}) // used by objectify
    public UserTrial() {
    }

    public UserTrial(Key<UserAccount> user, Key<Trial> trial, TrialWhen when) {
        this.user = user;
        this.trial = trial;
        this.when = when;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Key<UserAccount> getUser() {
        return user;
    }

    public void setUser(Key<UserAccount> user) {
        this.user = user;
    }

    public Key<Trial> getTrial() {
        return trial;
    }

    public void setTrial(Key<Trial> trial) {
        this.trial = trial;
    }

    public TrialWhen getWhen() {
        return when;
    }

    public void setWhen(TrialWhen when) {
        this.when = when;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTrial userTrial = (UserTrial) o;

        if (id != null ? !id.equals(userTrial.id) : userTrial.id != null) return false;
        if (trial != null ? !trial.equals(userTrial.trial) : userTrial.trial != null) return false;
        if (user != null ? !user.equals(userTrial.user) : userTrial.user != null) return false;
        if (when != null ? !when.equals(userTrial.when) : userTrial.when != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (trial != null ? trial.hashCode() : 0);
        result = 31 * result + (when != null ? when.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserTrial{" +
                "id=" + id +
                ", user=" + user +
                ", trial=" + trial +
                ", when=" + when +
                '}';
    }
}
