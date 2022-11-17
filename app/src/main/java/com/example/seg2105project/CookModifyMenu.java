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
                        showModifyCurrentMealDialog();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                return true;
            }
        });
    }

    private void showModifyCurrentMealDialog(){

    }

    private void showAddMealToMenuDialog() {
        AlertDialog.Builder addMealDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_add_menu_item,null);
        addMealDialog.setView(dialogView);

        addMealDialog
                .setCancelable(false)
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

        final Button addMealToMenuBtn = (Button) dialogView.findViewById(R.id.addItemMenu);

        final AlertDialog b = addMealDialog.create();
        b.show();

        addMealToMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToMenu(dialogView);
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

        Meal newMeal = new Meal(mealName.getText().toString(), mealIngredients.getText().toString(), mealAllergens.getText().toString(), mealDesc.getText().toString(), Double.parseDouble(mealPrice.getText().toString()), mealType.getText().toString(), mAuth.getUid());

        accountRef.child(mAuth.getUid()).child("menu").push().setValue(newMeal);

        Toast.makeText(CookModifyMenu.this, "Item has been added" , Toast.LENGTH_LONG).show();

    }

    private void navToCookHome(){
        Intent intent = new Intent(this, CookLoggedInScreen.class);
        startActivity(intent);
    }

}