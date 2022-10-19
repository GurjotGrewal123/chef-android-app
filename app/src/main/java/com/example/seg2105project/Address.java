package com.example.seg2105project;

public class Address {

    private String street;
    private String city;
    private String country;
    private String postalCode;

    /**
     *
     * @param street holds the street number and name
     * @param city holds the city of street
     * @param country holds the country of the street
     * @param postalCode holds the postal code associated with the street
     *
     * This class holds the Address for the Account class and its subsequent classes
     * Precondition: Street is an existing street in the following format: (Street number street name)
     *
     */

    public Address(String street, String city, String country, String postalCode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    public void setStreet(String street){
        this.street = street;
    }

    public String getStreet(){
        return street;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getCity(){
        return city;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getCountry(){
        return country;
    }

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    public String getPostalCode(){
        return postalCode;
    }

}
