package com.example.seg2105project;

import java.util.ArrayList;
import java.util.HashMap;

public class Client extends Account {

    private ArrayList<CuisineTypes> preferences;
    private Card cardInfo;
    private ArrayList<Meal> favMeals;
    private HashMap<Meal, Integer> ratings;

    public Client(String username, String password, String name, Address address, AccountType accountType) {
        super(username, password, name, address, accountType);
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
