package com.example.seg2105project;

import java.util.Date;

public class Complaint {
    private Date date;
    private String summary;
    private String user;
    private String cook;
    private String id;

    public Complaint() {

    }

    public Complaint(String sum, String userID, String cookID, String id) {
        date = new Date();
        this.user = userID;
        this.cook = cookID;
        this.summary = sum;
        this.id = id;
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
    public String getUser() {
        return user;
    }

    /**
     * @return the cook who the complaint was made against
     */
    public String getCook() {
        return cook;
    }

    /**
     * Sets the complaint against a specific cook. it can be changed by an admin
     *
     * @param cookID
     */
    public void setCook(String cookID) {
        this.cook = cookID;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }
}
