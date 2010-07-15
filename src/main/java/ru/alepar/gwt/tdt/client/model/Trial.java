package ru.alepar.gwt.tdt.client.model;

/**
 * User: alepar
 * Date: Jul 10, 2010
 * Time: 10:57:22 PM
 */
public class Trial {

    private Integer id;
    private String name;

    public Trial(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public static Trial from(Trial that) {
        Trial result = new Trial(that.id);
        result.name = that.name;
        return result;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trial trial = (Trial) o;

        if (id != null ? !id.equals(trial.id) : trial.id != null) return false;

        return true;
    }
}
