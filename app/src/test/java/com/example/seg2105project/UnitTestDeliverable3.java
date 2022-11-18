package com.example.seg2105project;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnitTestDeliverable3 {
    @Test
    public void mealNameChangeSuccessful(){
        Meal testMeal = new Meal("test","test","none","A test meal",10,"Example Meal","0","0");
        testMeal.setName("Test1");
        assertTrue(testMeal.getName() == "Test1");
    }

    @Test
    public void mealPriceChangeSuccessful(){
        Meal testMeal = new Meal("test","test","none","A test meal",10,"Example Meal","0","0");
        testMeal.setPrice(15.25);
        assertTrue(testMeal.getPrice() == 15.25);
    }

    @Test
    public void cookIsSuspended(){
        Cook testCook = new Cook("gRamsey@gmail.com","GRamsey","idiotSandwich", "Gordon Ramsey",new Address("1 Catherine Place","London","United Kingdom","SW1E 6DX"));
        testCook.setSuspension(true);
        assertTrue(testCook.isSuspension() == true);
    }

    @Test
    public void menuAdditionSuccessful(){
        Meal testMeal = new Meal("test","test","none","A test meal",10,"Example Meal","0","0");
        Cook testCook = new Cook("gRamsey@gmail.com","GRamsey","idiotSandwich", "Gordon Ramsey",new Address("1 Catherine Place","London","United Kingdom","SW1E 6DX"));
        testCook.addMeal(testMeal);
        assertTrue(testCook.getMeal().get(0) == testMeal);
    }
}


