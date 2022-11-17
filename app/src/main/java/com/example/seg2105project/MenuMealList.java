package com.example.seg2105project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MenuMealList extends ArrayAdapter<Meal> {

    private Activity context;
    List<Meal> meals;

    public MenuMealList(Activity context, List<Meal> meals){
        super(context, R.layout.activity_menu_meal_list, meals);
        this.context = context;
        this.meals = meals;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewMenuMeals = inflater.inflate(R.layout.activity_menu_meal_list,null,true);

        TextView mealName = (TextView) listViewMenuMeals.findViewById(R.id.menuMealName);
        TextView mealPrice = (TextView) listViewMenuMeals.findViewById(R.id.menuMealPrice);

        Meal meal = meals.get(position);

        mealName.setText("Meal Name: " + meal.getName());

        mealPrice.setText("Price : " + meal.getPrice());
        return listViewMenuMeals;
    }

}
