package com.example.seg2105project;

import java.util.ArrayList;

public class Meal {

    private ArrayList<CuisineTypes> types;
    private String name;
    private double price;
    private ArrayList<String> ingredients;
    private ArrayList<String> allergens;
    private String description;


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
    public Meal(String name, ArrayList<String> ingredients, ArrayList<String> allergens,
                String description, double price, ArrayList<CuisineTypes> types) {
        this.name = name;
        this.ingredients = ingredients;
        this.allergens = allergens;
        this.description = description;
        this.price = price;
        this.types = types;
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
    public void changePrice(double price) {
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
     * adds a single ingredient to the list of ingredients
     * @param ing
     * @return
     */
    public boolean addIngredient(String ing){
        return ingredients.add(ing);
    }
    /**
     * deletes a single ingredient
     * @param ing
     * @return true if the ingredient is deleted and false if not found
     * it is not necessary to delete the entire list at once as with 0 ingredients there is no
     * meal.
     */
    public boolean deleteIngredient(String ing){
        return ingredients.remove(ing);
    }
    /**
     * will update the entire list of ingredients with a new list of ingredients
     * @param theList
     */
    public void addIngredientList(ArrayList<String> theList){
        this.ingredients = theList;
    }
    /**
     *
     * @return gets the list of cuisineTypes associated with a specific meal
     */
    public ArrayList<CuisineTypes> getTypes() {
        return types;
    }
    /**
     *
     * @return the complete list of ingredients
     */
    public ArrayList<String> getIngredients() {
        return ingredients;
    }
    /**
     *
     * @return the list of allergens associated with a meal
     */
    public ArrayList<String> getAllergens(){
        return allergens;
    }
    /**
     * adds a new allergen to a meal
     * @param aller
     * @return
     */
    public boolean addAllergen(String aller){
        return allergens.add(aller);
    }
    /**
     *removes an allergen from a meal, it is assumed that a new ingredient could
     * be used so that a meal is allergen free
     * @param aller
     * @return
     */
    public boolean removeAllergen(String aller){
        return allergens.remove(aller);
    }
    /**
     * overides the current description of a meal with a new one
     * @param theText
     */
    public void updateDescription(String theText){
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
     * @param type
     */
    public boolean addType(CuisineTypes type) {
        return types.add(type);
    }
    /**
     * removes a specific type
     * @param type
     * @return
     */
    public boolean removeType(CuisineTypes type){
        return types.remove(type);
    }

}
