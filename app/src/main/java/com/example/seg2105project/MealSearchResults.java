package com.example.seg2105project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
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
import java.util.Locale;

public class MealSearchResults extends AppCompatActivity {

    ListView meals;
    List<Meal> clientMealList;

    DatabaseReference allMealRef;
    DatabaseReference accountRef;

    private Button backButton;

    private String mealNameParam;
    private int mealPriceParam;
    private String mealTypeParam;
    boolean suspensionNoti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_search_results);

        allMealRef = FirebaseDatabase.getInstance().getReference("allOfferedMeals");
        accountRef = FirebaseDatabase.getInstance().getReference("accounts");
        meals = findViewById(R.id.clientMealList);
        clientMealList = new ArrayList<>();
        onItemLongClick();

        Bundle b = getIntent().getExtras();
        mealNameParam = b.getString("mealNameParam");
        mealPriceParam = b.getInt("mealPriceParam");
        mealTypeParam = b.getString("mealTypeParam");

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backNav();
            }
        });
    }

    protected void onStart(){
        super.onStart();
        allMealRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clientMealList.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){

                    Meal meal = postSnapshot.getValue(Meal.class);
                    if (!checkSuspendStatus(meal)) {
                        if (meal.getName().toLowerCase().contains(mealNameParam.toLowerCase())) {
                            if (meal.getTypes().toLowerCase().contains(mealTypeParam.toLowerCase())){
                                if (mealPriceParam == -1){
                                    clientMealList.add(meal);
                                }
                                else if (meal.getPrice() >= mealPriceParam){
                                    clientMealList.add(meal);
                                }
                            }
                        }
                    }
                }

                MenuMealList menuMealAdapter = new MenuMealList(MealSearchResults.this, clientMealList);
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
                Meal meal = clientMealList.get(i);

                accountRef.child(meal.getCookAssignedID()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Cook cook = snapshot.getValue(Cook.class);
                        showModifyCurrentMealListDialog();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                return true;
            }
        });
    }

    private boolean checkSuspendStatus(Meal meal){
        accountRef.child(meal.getCookAssignedID()).child("suspension").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                suspensionNoti = snapshot.getValue(Boolean.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return suspensionNoti;
    }

    private void backNav(){
        Intent intent = new Intent(this, MealSearchParamaterScreen.class);
        startActivity(intent);
    }

    public void showModifyCurrentMealListDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_meal_preview_screen, null);
        dialogBuilder.setView(dialogView);


        final Button noButton = dialogView.findViewById(R.id.removeMealFromMenu);
        final Button yesButton = dialogView.findViewById(R.id.addMealtoOfferedList);
        final AlertDialog b = dialogBuilder.create();
        b.show();
    }
}