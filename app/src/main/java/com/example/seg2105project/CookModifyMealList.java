package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CookModifyMealList extends AppCompatActivity {

    private Button MealListHomeNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_modify_meal_list);

        MealListHomeNav = findViewById(R.id.MealListHomeBtn);
        MealListHomeNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navHome();
            }
        });
    }

    public void navHome(){
        Intent intent = new Intent(this, CookLoggedInScreen.class);
        startActivity(intent);
    }
}