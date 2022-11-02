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

    public void createSuspension(Cook cook , Complaint c) {
        cook.isSuspension();
        if(c!=null) {
            cook.addComplaints(c);
        }
    }

    public void approveCook(Cook cook) {
        cook.endSuspension();
    }

    // currently the below methods are not required for the current iteration of the app and will be implemented at a later sprint
    public void deleteAccount() {
        //WORK IN PROGRESS
    }

    public void reviewComplaint(Cook cook) {
        cook.getComplaints();
        //WORK IN PROGRESS

    }

    public void banAccount() {
        //WORK IN PROGRESS
    }

}
