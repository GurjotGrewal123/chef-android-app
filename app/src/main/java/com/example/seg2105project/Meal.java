package com.example.seg2105project;

import java.util.ArrayList;

public class Meal {

    private ArrayList<CuisineTypes> types;
    private String name;
    private double price;

    /**
     *
     * @param name holds the name of the meal as assigned to by a Cook
     * @param price holds the price as assigned to by a Cook
     * @param types holds the type the meal falls under within the dish types available in the CuisineTypes enum
     *
     * This class is the core of the app, as this is what the Client instances will be ordering from the Cook instances
     * Precondition: types is a parameter that is found in CuisineTypes
     *
     */

    public Meal(String name, double price, ArrayList<CuisineTypes> types) {
        this.name = name;
        this.price = price;
        this.types = types;
    }

    public void changePrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<CuisineTypes> getTypes() {
        return types;
    }

    public void addType(CuisineTypes type) {
        types.add(type);
    }

}
