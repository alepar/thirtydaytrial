package ru.alepar.gwt.tdt.client.model;

/**
 * User: alepar
 * Date: Jul 10, 2010
 * Time: 10:57:22 PM
 */
public class Trial {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Trial from(Trial that) {
        Trial result = new Trial();
        result.name = that.name;
        return result;
    }
}
