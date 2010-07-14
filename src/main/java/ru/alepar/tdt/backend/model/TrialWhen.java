package ru.alepar.tdt.backend.model;

import java.util.Date;

/**
 * User: looser
 * Date: 11.07.2010
 */
public class TrialWhen {
    String id;
    
    Date startTime;
    Date endTime;
    String recurData;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRecurData() {
        return recurData;
    }

    public void setRecurData(String recurData) {
        this.recurData = recurData;
    }
}
