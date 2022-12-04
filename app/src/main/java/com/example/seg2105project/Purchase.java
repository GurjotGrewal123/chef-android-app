package com.example.seg2105project;

public class Purchase {

    private Meal meal;
    private String id;
    private String clientID;
    private String status;

    public Purchase(){

    }

    public Purchase(Meal meal, String id, String clientID){
        this.clientID = clientID;
        this.id = id;
        this.meal = meal;
        this.status = "pending";

    }

    public Meal getMeal() {
        return meal;
    }

    public String getClientID() {
        return clientID;
    }

    public String getId() {
        return id;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
