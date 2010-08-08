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

    private String googleDataSessionToken;

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

    public String getGoogleDataSessionToken() {
        return googleDataSessionToken;
    }

    public void setGoogleDataSessionToken(String googleDataSessionToken) {
        this.googleDataSessionToken = googleDataSessionToken;
    }

    public void setUserKey(Key<UserAccount> userKey) {
        this.userKey = userKey;
    }
}
