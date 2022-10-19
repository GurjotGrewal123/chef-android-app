package com.example.seg2105project;

import java.util.ArrayList;
import java.util.HashMap;

public class Client extends Account {

    private ArrayList<CuisineTypes> preferences;
    private Card cardInfo;
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
