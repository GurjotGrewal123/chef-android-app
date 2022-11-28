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
import android.widget.TextView;
import android.widget.Toast;

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
    FirebaseAuth mAuth;

    private Button backButton;

    private String mealNameParam;
    private int mealPriceParam;
    private String mealTypeParam;
    boolean suspensionNoti;
    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_search_results);

        n = 0;
        mAuth = FirebaseAuth.getInstance();
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
                        showModifyCurrentMealListDialog(cook, meal);
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

    public void showModifyCurrentMealListDialog(Cook cook, Meal meal){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_meal_preview_screen, null);
        dialogBuilder.setView(dialogView);


        final Button noButton = dialogView.findViewById(R.id.cancelMealButton);
        final Button buyButton = dialogView.findViewById(R.id.buyMealButton);
        final AlertDialog b = dialogBuilder.create();

        final TextView mealName = dialogView.findViewById(R.id.mealName);
        final TextView mealPrice = dialogView.findViewById(R.id.mealPrice);
        final TextView mealType = dialogView.findViewById(R.id.mealType);
        final TextView mealIngredients = dialogView.findViewById(R.id.mealIngredients);
        final TextView mealAllergens = dialogView.findViewById(R.id.mealAllergens);
        final TextView mealDescription = dialogView.findViewById(R.id.mealDescription);
        final TextView cookName = dialogView.findViewById(R.id.cookUsername);
        final TextView cookRating = dialogView.findViewById(R.id.cookRating);

        mealName.setText("Meal Name: " + meal.getName());
        mealPrice.setText("Price: " + meal.getPrice());
        mealType.setText("Type: " + meal.getTypes());
        mealAllergens.setText("Allergens: " + meal.getAllergens());
        mealIngredients.setText("Ingredients: " + meal.getIngredients());
        mealDescription.setText("Meal Description: " + meal.getDescription());
        cookName.setText("Cook: " + cook.getName());

        b.show();

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                purchaseMeal(meal);
                b.dismiss();
            }
        });
    }

    private void purchaseMeal(Meal meal){
        int num = getTotalPurchases(meal) + 1;
        accountRef.child(meal.getCookAssignedID()).child("totalPurchases").setValue(num);
        accountRef.child(meal.getCookAssignedID()).child("yourPurchaseRequests").push().setValue(meal);
        accountRef.child(mAuth.getUid()).child("yourOrders").push().setValue(meal);
        Toast.makeText(MealSearchResults.this, "Your order has been processed. Please check your orders to view its status." , Toast.LENGTH_LONG).show();
    }

    private int getTotalPurchases(Meal meal){
        accountRef.child(meal.getCookAssignedID()).child("totalPurchases").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                n = snapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return n;
    }

}