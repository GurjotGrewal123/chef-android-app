package com.example.seg2105project;

import java.util.ArrayList;

public class Meal {

    private String types;
    private String name;
    private double price;
    private String ingredients;
    private String allergens;
    private String description;
    private String cookAssignedID;
    private String id;


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
    public Meal(String name, String ingredients, String allergens,
                String description, double price, String types, String cookAssignedID, String id) {
        this.name = name;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.description = description;
        this.price = price;
        this.types = types;
        this.cookAssignedID = cookAssignedID;
        this.id = id;
    }
    /**
     * empty constructor, items can be added later
     */
    public Meal(){

    }
    /**
     *
     * @return the name of the specific meal
     */
    public String getName() {
        return name;
    }
    /**
     * sets the name of the meal, it is resonable to assume that the names of meals may be updated
     * or changed
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * prices may be updated and this method will allow for the price to be updated
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     *
     * @return the price of a specific meal
     */
    public double getPrice() {
        return price;
    }
    /**
     *
     * @return gets the list of cuisineTypes associated with a specific meal
     */
    public String getTypes() {
        return types;
    }
    /**
     *
     * @return the complete list of ingredients
     */
    public String getIngredients() {
        return ingredients;
    }
    /**
     *
     * @return the list of allergens associated with a meal
     */
    public String getAllergens(){
        return allergens;
    }
    /**
     * adds a new allergen to a meal
     * @param aller
     * @return
     */
    public void setAllergens(String aller){
        this.allergens = aller;
    }

    public void setDescription(String theText){
        this.description = theText;
    }
    /**
     *
     * @return the description of a specific meal.
     */
    public String getDescription(){
        return description;
    }
    /**
     * adds an additional type to the list of cuisines
     * @param types
     */
    public void setTypes(String types) {
         this.types = types;
    }

    public String getCookAssignedID(){
        return this.cookAssignedID;
    }

    public void setCookAssignedID(String cookAssignedID){
        this.cookAssignedID = cookAssignedID;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }

}
