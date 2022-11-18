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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CookModifyMenu extends AppCompatActivity {

    private Button AddMealButton;
    private Button MenuHomeButtonNav;

    ListView meals;
    List<Meal> menuMealList;

    DatabaseReference accountRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_modify_menu);

        accountRef = FirebaseDatabase.getInstance().getReference("accounts");
        mAuth = FirebaseAuth.getInstance();

        meals = findViewById(R.id.menuMeals);
        menuMealList = new ArrayList<>();
        onItemLongClick();

        AddMealButton = findViewById(R.id.addMealtoMenu);
        AddMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddMealToMenuDialog();
            }
        });

        MenuHomeButtonNav = findViewById(R.id.MenuHomeBtn);
        MenuHomeButtonNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navToCookHome();
            }
        });

    }

    protected void onStart(){
        super.onStart();
        accountRef.child(mAuth.getUid()).child("menu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                menuMealList.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){

                    Meal meal = postSnapshot.getValue(Meal.class);
                    menuMealList.add(meal);
                }
                MenuMealList menuMealAdapter = new MenuMealList(CookModifyMenu.this, menuMealList);
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
                Meal meal = menuMealList.get(i);

                accountRef.child(mAuth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Cook userProfile = snapshot.getValue(Cook.class);
                        showModifyCurrentMealDialog(meal);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                return true;
            }
        });
    }

    private void showModifyCurrentMealDialog(Meal meal){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.remove_menu_item_dialog,null);
        dialogBuilder.setView(dialogView);



        final Button addMealToList = dialogView.findViewById(R.id.addMealtoOfferedList);
        final Button removeMeal = dialogView.findViewById(R.id.removeMealFromMenu);
        final Button cancelDialog = dialogView.findViewById(R.id.cancelMenuDialog);
        final TextView mealName = dialogView.findViewById(R.id.mealInMenuName);
        final TextView mealPrice = dialogView.findViewById(R.id.mealInMenuPrice);
        final TextView mealType = dialogView.findViewById(R.id.mealInMenuType);
        final TextView mealIng = dialogView.findViewById(R.id.mealInMenuIngredients);
        final TextView mealAllergens = dialogView.findViewById(R.id.mealInMenuAllergens);

        mealName.setText("Meal Name: "+meal.getName());
        mealPrice.setText("Meal Price: "+meal.getPrice());
        mealIng.setText("Meal Ingredients: "+meal.getIngredients());
        mealType.setText("Meal Type: "+meal.getTypes());
        mealAllergens.setText("Meal Allergens: "+meal.getAllergens());

        final AlertDialog b = dialogBuilder.create();
        b.show();

        addMealToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMealToList(meal);
            }
        });

        removeMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeMealFromMenu(meal);
            }
        });

        cancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });

    }

    private void addMealToList(Meal meal){
        accountRef.child(mAuth.getUid()).child("offeredMeals").child(meal.getId()).setValue(meal);
        Toast.makeText(CookModifyMenu.this, "Meal has been added to the offered meals list" , Toast.LENGTH_LONG).show();

    }

    private void removeMealFromMenu(Meal meal){

        accountRef.child(mAuth.getUid()).child("offeredMeals").child(meal.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Toast.makeText(CookModifyMenu.this, "This meal is in your offered meals. You cannot remove it." , Toast.LENGTH_LONG).show();
                }
                else{
                    accountRef.child(mAuth.getUid()).child("menu").child(meal.getId()).removeValue();
                    Toast.makeText(CookModifyMenu.this, "Meal has been removed" , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void showAddMealToMenuDialog() {
        AlertDialog.Builder addMealDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_add_menu_item,null);
        addMealDialog.setView(dialogView);

        final Button addMealToMenuBtn = (Button) dialogView.findViewById(R.id.addItemMenu);
        final Button cancelAddMealMenuBtn = (Button) dialogView.findViewById(R.id.cancelAddMenuItem);

        final AlertDialog b = addMealDialog.create();
        b.show();

        addMealToMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToMenu(dialogView);
            }
        });

        cancelAddMealMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });
    }

    private void addItemToMenu(View dialogView){
        final TextView mealName = (TextView) dialogView.findViewById(R.id.enterMealName);
        final TextView mealType = (TextView) dialogView.findViewById(R.id.enterMealType);
        final TextView mealIngredients = (TextView) dialogView.findViewById(R.id.enterIngredients);
        final TextView mealAllergens = (TextView) dialogView.findViewById(R.id.enterAllergens);
        final TextView mealPrice = (TextView) dialogView.findViewById(R.id.enterPrice);
        final TextView mealDesc = (TextView) dialogView.findViewById(R.id.enterItemDescription);

        String id = accountRef.child(mAuth.getUid()).child("menu").push().getKey();
        Meal newMeal = new Meal(mealName.getText().toString(), mealIngredients.getText().toString(), mealAllergens.getText().toString(), mealDesc.getText().toString(), Double.parseDouble(mealPrice.getText().toString()), mealType.getText().toString(), mAuth.getUid(), id);

        accountRef.child(mAuth.getUid()).child("menu").child(id).setValue(newMeal);

        Toast.makeText(CookModifyMenu.this, "Meal has been added to the menu" , Toast.LENGTH_LONG).show();

    }

    private void navToCookHome(){
        Intent intent = new Intent(this, CookLoggedInScreen.class);
        startActivity(intent);
    }

}