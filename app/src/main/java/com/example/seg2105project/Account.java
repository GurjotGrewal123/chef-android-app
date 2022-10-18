package com.example.seg2105project;

public abstract class Account {
    private String username;
    private String password;
    private String name;
    private Address address;
    private AccountType accountType;

    protected Account(String username, String password, String name, Address address,
            AccountType accountType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountType getType() {
        return accountType;
    }

    public void setType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Address getAddress() {
        return address;
    }

    public void setUsername(Address address) {
        this.address = address;
    }

}