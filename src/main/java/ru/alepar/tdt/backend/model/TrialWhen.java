package ru.alepar.tdt.backend.model;

import java.io.Serializable;

/**
 * User: looser
 * Date: 11.07.2010
 */
public class TrialWhen implements Serializable {
    String data;

    @SuppressWarnings({"UnusedDeclaration"}) // used by objectify
    public TrialWhen() {
    }

    public TrialWhen(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrialWhen trialWhen = (TrialWhen) o;

        if (data != null ? !data.equals(trialWhen.data) : trialWhen.data != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TrialWhen{" +
                "data='" + data + '\'' +
                '}';
    }
}
