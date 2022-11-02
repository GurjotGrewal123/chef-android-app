package com.example.seg2105project;

import java.util.Date;

public class Complaint {
    private Date date;
    private String summary;
    private Client user;
    private Cook cook;

    public Complaint() {

    }

    public Complaint(String sum, Client user, Cook cook) {
        date = new Date();
        this.setSummary(sum);
        this.user = user;
        this.setCook(cook);
        this.setSummary(sum);
    }

    /**
     * returns the date
     *
     * @return
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return the information on the client who submitted the complaint is listed
     */
    public Client getUser() {
        return user;
    }

    /**
     * @return the cook who the complaint was made against
     */
    public Cook getCook() {
        return cook;
    }

    /**
     * Sets the complaint against a specific cook. it can be changed by an admin
     *
     * @param cook
     */
    public void setCook(Cook cook) {
        this.cook = cook;
    }
}
