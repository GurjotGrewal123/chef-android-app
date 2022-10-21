package com.example.seg2105project;

public abstract class Account {
    private String email;
    private String username;
    private String password;
    private String name;
    private Address address;
    private AccountType type;

    /**
     * @param username holds the account's username
     * @param password holds the account's password
     * @param name     holds the personal name associated with its account instance
     * @param address  holds the address associated with its account instance
     *                 <p>
     *                 This class is an abstract class which the classes Client,
     *                 Administrator and Cook are based off
     */

    protected Account(String email, String username, String password, String name, Address address, AccountType type) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.type = type;
    }

    protected Account(){

    }

    /**
     * Sets the users email address
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * returns the acount email address
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the username of the account should not be changed in a normal situation
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * returns the username of the account
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * gets the password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets the password string for the account
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * returns the users name for the account and it is different from the username parameter
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * sets the users name and is seperater from the username parameter
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns the address associated with the account, it is based on the class Address
     * @return
     */
    public Address getAddress() {
        return address;
    }

    /**
     * sets the address and is based on the class Address
     * @param address
     */
    public void setAddress(Address address){
        this.address = address;
    }

    /**
     * sets the accountType based on a specified account structure at present the types are Client Admin and Cook
     * @param type
     */
    public void setType(AccountType type) {
        this.type = type;
    }

    /**
     * returns the type of the account which should match the SubClass type.
     * @return
     */
    public AccountType getType(){
        return type;
    }
}