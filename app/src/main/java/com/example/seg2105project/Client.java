package com.example.seg2105project;

import java.util.ArrayList;
import java.util.HashMap;

public class Client extends Account {
    /**
     * this is a pregerence so help the user limit their searches to a specific cuisine
     */
    private ArrayList<CuisineTypes> preferences;
    /**
     * this is from the class Card and it hold the clients credit card info
     */
    private Card cardInfo;
    /**
     * holds objects of type meal so that the user can keep a list of their favorite meals
     */
    private ArrayList<Meal> favMeals;
    private HashMap<Meal, Integer> ratings;

    /**
     *
     * @param username
     * @param password
     * @param name
     * @param address
     */

    public Client(String email, String username, String password, String name, Address address, Card card) {
        super(email, username, password, name, address, AccountType.CLIENT);
        cardInfo = card;
    }

    public Client(){

    }

    public void addPreferences(CuisineTypes preference) {
        preferences.add(preference);
    }

    public ArrayList<CuisineTypes> getPreferences() {
        return preferences;
    }

    public void addPayment(Card card) {
        cardInfo = card;
    }

    public Card getPayment() {
        return cardInfo;
    }

    //public void createComplaint(Complaint c){ c.getCook().addComplaints(c);}

    public void addFavMeal(Meal meal) {
        favMeals.add(meal);
    }

    public ArrayList<Meal> getFavMeals() {
        return favMeals;
    }

    public void giveRating(Meal meal, Integer rating) {
        if (!ratings.containsKey(meal)) {
            ratings.put(meal, rating);
        } else {
            ratings.replace(meal, rating);
        }
    }

    public HashMap<Meal, Integer> getRatings() {
        return ratings;
    }

    public void purchaseMeal(Meal meal, Cook cooker) {

    }

}
