package ru.alepar.tdt.backend.jdo.model;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

/**
 * User: looser
 * Date: 11.07.2010
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class UserTrial extends Trial {
    @Persistent
    TrialWhen trialWhen;

    public TrialWhen getTrialWhen() {
        return trialWhen;
    }

    public void setTrialWhen(TrialWhen trialWhen) {
        this.trialWhen = trialWhen;
    }
}
