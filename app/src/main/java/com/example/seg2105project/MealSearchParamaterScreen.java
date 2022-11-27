package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MealSearchParamaterScreen extends AppCompatActivity {

    Button searchForMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_search_paramater_screen);

        final TextView mealNameParam = findViewById(R.id.enterMealNameSearch);

        searchForMeal = findViewById(R.id.searchForMeal);
        searchForMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMeals(mealNameParam.getText().toString());
            }
        });
    }

    private void openMeals(String mealNameParam){
        Intent intent = new Intent(this, MealSearchResults.class);
        intent.putExtra("mealNameParam", mealNameParam);
        startActivity(intent);
    }
}