package com.example.seg2105project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
        meals.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Meal meal = offeredMealList.get(i);

                accountRef.child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Cook userProfile = snapshot.getValue(Cook.class);
                        showModifyCurrentMealListDialog(meal);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                return true;
            }
        });
    }
    private void showModifyCurrentMealListDialog(Meal meal) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.remove_meal_list_item_dialog, null);
        dialogBuilder.setView(dialogView);


        final Button noButton = dialogView.findViewById(R.id.removeMealFromMenu);
        final Button yesButton = dialogView.findViewById(R.id.addMealtoOfferedList);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //navToMealList();
                b.dismiss();
            }
        });
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItemFromList(meal);

            }
        });

    }
    public void navHome(){
        Intent intent = new Intent(this, CookLoggedInScreen.class);
        startActivity(intent);
    }
    public void navToMealList(){
        Intent intent = new Intent(this, CookModifyMealList.class);
        startActivity(intent);
    }
    public void removeItemFromList(Meal meal){
        accountRef.child(mAuth.getUid()).child("offeredMeals").child(meal.getId()).removeValue();
    }
}