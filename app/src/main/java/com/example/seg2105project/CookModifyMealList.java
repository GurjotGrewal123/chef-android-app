package com.example.seg2105project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CookModifyMealList extends AppCompatActivity {

    private Button MealListHomeNav;

    ListView meals;
    List<Meal> offeredMealList;

    DatabaseReference accountRef;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_modify_meal_list);

        accountRef = FirebaseDatabase.getInstance().getReference("accounts");
        mAuth = FirebaseAuth.getInstance();

        meals = findViewById(R.id.offeredMeals);
        offeredMealList = new ArrayList<>();
        onItemLongClick();

        MealListHomeNav = findViewById(R.id.MealListHomeBtn);
        MealListHomeNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navHome();
            }
        });
    }

    protected void onStart(){
        super.onStart();
        accountRef.child(mAuth.getUid()).child("offeredMeals").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                offeredMealList.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){

                    Meal meal = postSnapshot.getValue(Meal.class);
                    offeredMealList.add(meal);
                }
                MenuMealList menuMealAdapter = new MenuMealList(CookModifyMealList.this, offeredMealList);
                meals.setAdapter(menuMealAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void onItemLongClick(){

    }

    public void navHome(){
        Intent intent = new Intent(this, CookLoggedInScreen.class);
        startActivity(intent);
    }
}