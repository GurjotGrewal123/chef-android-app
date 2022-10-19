package com.example.seg2105project;

public abstract class Account {
    private String email;
    private String username;
    private String password;
    private String name;
    private Address address;

    /**
     * @param username holds the account's username
     * @param password holds the account's password
     * @param name     holds the personal name associated with its account instance
     * @param address  holds the address associated with its account instance
     *                 <p>
     *                 This class is an abstract class which the classes Client,
     *                 Administrator and Cook are based off
     */

    protected Account(String email, String username, String password, String name, Address address) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address){
        this.address = address;
    }
}