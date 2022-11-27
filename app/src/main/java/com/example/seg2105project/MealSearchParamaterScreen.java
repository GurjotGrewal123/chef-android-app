package com.example.seg2105project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MealSearchParamaterScreen extends AppCompatActivity {

    private Button searchForMeal;
    private Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_search_paramater_screen);

        final TextView mealNameParam = findViewById(R.id.enterMealNameSearch);
        final TextView mealPriceParam = findViewById(R.id.enterMealMaxPriceSearch);
        final TextView mealTypeParam = findViewById(R.id.enterMealTypeSearch);


        searchForMeal = findViewById(R.id.searchForMeal);
        searchForMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMeals(mealNameParam.getText().toString(), mealPriceParam.getText().toString(), mealTypeParam.getText().toString());
            }
        });

        homeButton = findViewById(R.id.cancelMealSearch);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeNav();
            }
        });
    }

    private void openMeals(String mealNameParam, String mealPriceParam, String mealTypeParam){
        int mealMaxPrice = -1;
        if (!mealPriceParam.isEmpty()){
            mealMaxPrice = Integer.parseInt(mealPriceParam);
        }
        Intent intent = new Intent(this, MealSearchResults.class);
        intent.putExtra("mealNameParam", mealNameParam);
        intent.putExtra("mealPriceParam", mealMaxPrice);
        intent.putExtra("mealTypeParam", mealTypeParam);
        startActivity(intent);
    }

    private void homeNav(){
        Intent intent = new Intent(this, LoggedInScreen.class);
        startActivity(intent);
    }

}