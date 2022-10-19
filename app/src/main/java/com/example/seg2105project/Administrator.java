package com.example.seg2105project;

public class Administrator extends Account {

    /**
     *
     * @param username
     * @param password
     * @param name
     * @param address
     *
     * This class extends our abstract Account class, and is the class responsible for allowing users
     * of the Admin instance to moderate our application
     *
     */

    public Administrator(String email, String username, String password, String name, Address address) {
        super(email, username, password, name, address, AccountType.ADMIN);
    }

    public Administrator(){

    }

    public void deleteAccount() {
        //WORK IN PROGRESS
    }

    public void reviewComplaint() {
        //WORK IN PROGRESS
    }

    public void createSuspension() {
        //WORK IN PROGRESS
    }

    public void approveCook() {
        //WORK IN PROGRESS
    }

    public void banAccount() {
        //WORK IN PROGRESS
    }

}
