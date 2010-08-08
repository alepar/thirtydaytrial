package ru.alepar.tdt.backend.model.user;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;
import ru.alepar.tdt.backend.model.trial.Trial;
import ru.alepar.tdt.backend.model.trial.TrialWhen;

import javax.persistence.Embedded;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;


/**
 * User: looser
 * Date: 11.07.2010
 */
public class UserTrial implements Serializable {
    @Id
    private Long id;

    @Parent
    private Key<UserAccount> userKey;

    private Key<Trial> trialKey;

    @Transient
    private Trial trial;

    @Embedded
    private TrialWhen when;

    @SuppressWarnings({"UnusedDeclaration"}) // used by serialization
    public UserTrial() {
    }

    public UserTrial(Key<UserAccount> user, Key<Trial> trialKey, TrialWhen when) {
        this.userKey = user;
        this.trialKey = trialKey;
        this.when = when;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Key<UserAccount> getUserKey() {
        return userKey;
    }

    public void setUserKey(Key<UserAccount> userKey) {
        this.userKey = userKey;
    }

    public Key<Trial> getTrialKey() {
        return trialKey;
    }

    public void setTrialKey(Key<Trial> trialKey) {
        this.trialKey = trialKey;
    }

    public TrialWhen getWhen() {
        return when;
    }

    public void setWhen(TrialWhen when) {
        this.when = when;
    }

    public Trial getTrial() {
        return trial;
    }

    public void setTrial(Trial trial) {
        this.trial = trial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTrial userTrial = (UserTrial) o;

        if (id != null ? !id.equals(userTrial.id) : userTrial.id != null) return false;
        if (trialKey != null ? !trialKey.equals(userTrial.trialKey) : userTrial.trialKey != null) return false;
        if (userKey != null ? !userKey.equals(userTrial.userKey) : userTrial.userKey != null) return false;
        if (when != null ? !when.equals(userTrial.when) : userTrial.when != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userKey != null ? userKey.hashCode() : 0);
        result = 31 * result + (trialKey != null ? trialKey.hashCode() : 0);
        result = 31 * result + (when != null ? when.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserTrial{" +
                "id=" + id +
                ", userKey=" + userKey +
                ", trialKey=" + trialKey +
                ", when=" + when +
                '}';
    }

    public static UserTrial from(UserTrial src) {
        UserTrial clone = new UserTrial();
        clone.id = src.id;
        clone.trial = src.trial;
        clone.trialKey = src.trialKey;
        clone.userKey = src.userKey;
        clone.when = src.when;
        return clone;
    }
}
