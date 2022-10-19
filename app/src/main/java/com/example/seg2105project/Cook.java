package com.example.seg2105project;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Cook extends Account {

    private ArrayList<String> complaintsAgainst;
    private ArrayList<Meal> menu;
    private HashMap<Meal, Integer> ratings;
    private ArrayList<CuisineTypes> cuisine;

    /**
     *
     * @param username
     * @param password
     * @param name
     * @param address
     *
     */

    public Cook(String email, String username, String password, String name, Address address) {
        super(email, username, password, name, address);
    }

    public void addCuisineType(CuisineTypes type) {
        cuisine.add(type);
    }

    public ArrayList<CuisineTypes> getCuisineTypes() {
        return cuisine;
    }

    public void addComplaints(String complaintMsg) {
        complaintsAgainst.add(complaintMsg);
    }

    public ArrayList<String> getComplaints() {
        return complaintsAgainst;
    }

    public void addMeal(Meal meal) {
        menu.add(meal);
    }

    public void removeMeal(Meal meal) {
        menu.remove(meal);
    }

    public ArrayList<Meal> getMeal() {
        return menu;
    }

    public void addRating(Meal meal, Integer rating) {
        if (!ratings.containsKey(meal)) {
            ratings.put(meal, rating);
        } else {
            ratings.replace(meal, rating);
        }
    }

    public HashMap<Meal, Integer> getRatings() {
        return ratings;
    }

}
