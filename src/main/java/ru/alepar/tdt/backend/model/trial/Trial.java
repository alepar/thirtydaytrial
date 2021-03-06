package ru.alepar.tdt.backend.model.trial;

import javax.persistence.Id;
import java.io.Serializable;


/**
 * User: looser
 * Date: 11.07.2010
 */
public class Trial implements Serializable {

    @Id 
    private Long id;
    private String title;
    private String content;

    @SuppressWarnings({"UnusedDeclaration"}) // used by serialization
    public Trial() {
    }

    public Trial(Long id) {
        this.id = id;
    }

    public Trial(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trial trial = (Trial) o;

        if (content != null ? !content.equals(trial.content) : trial.content != null) return false;
        if (id != null ? !id.equals(trial.id) : trial.id != null) return false;
        if (title != null ? !title.equals(trial.title) : trial.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Trial{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public static Trial from(Trial that) {
        Trial result = new Trial();
        result.id = that.id;
        result.title = that.title;
        result.content = that.content;
        return result;
    }
}
