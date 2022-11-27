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

    Button searchForMeal;
    Button mealType;
    Button cancel;

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

        mealType = findViewById(R.id.selectMealTypeD);
        mealType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMealTypeDialog();
            }
        });
        cancel = findViewById(R.id.cancelMealSearch);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });
    }

    private void openMeals(String mealNameParam){
        Intent intent = new Intent(this, MealSearchResults.class);
        intent.putExtra("mealNameParam", mealNameParam);
        startActivity(intent);
    }

    public void showMealTypeDialog(){
        AlertDialog.Builder showMealType = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.select_meal_type_dialog,null);
        showMealType.setView(dialogView);

//        final Button  = (Button) dialogView.findViewById(R.id.);
//        final Button  = (Button) dialogView.findViewById(R.id.);

        final AlertDialog b = showMealType.create();
        b.show();
    }

    public void cancel(){
        Intent intent = new Intent(this, LoggedInScreen.class);
        startActivity(intent);
    }
}