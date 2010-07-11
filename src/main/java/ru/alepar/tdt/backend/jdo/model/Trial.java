package ru.alepar.tdt.backend.jdo.model;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * User: looser
 * Date: 11.07.2010
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Trial {
    @PrimaryKey
    String id;

    @Persistent
    String title;

    @Persistent
    String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
