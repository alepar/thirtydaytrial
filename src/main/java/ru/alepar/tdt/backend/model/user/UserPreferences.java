package ru.alepar.tdt.backend.model.user;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Parent;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * User: alepar
 * Date: Aug 7, 2010
 * Time: 4:50:04 PM
 */
public class UserPreferences implements Serializable {

    @Id
    private Long id;

    @Parent
    private Key<UserAccount> userKey;

    private boolean googleCalIntegrationEnabled = false;

    private String googleCalSessionToken;

    public UserPreferences() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isGoogleCalIntegrationEnabled() {
        return googleCalIntegrationEnabled;
    }

    public void setGoogleCalIntegrationEnabled(boolean googleCalIntegrationEnabled) {
        this.googleCalIntegrationEnabled = googleCalIntegrationEnabled;
    }

    public String getGoogleCalSessionToken() {
        return googleCalSessionToken;
    }

    public void setGoogleCalSessionToken(String googleCalSessionToken) {
        this.googleCalSessionToken = googleCalSessionToken;
    }

    public void setUserKey(Key<UserAccount> userKey) {
        this.userKey = userKey;
    }
}
