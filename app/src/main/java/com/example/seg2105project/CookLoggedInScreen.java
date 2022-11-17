package com.example.seg2105project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class CookLoggedInScreen extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private Button logOutButton;
    DatabaseReference susCheckRef;
    private Button MenuScreenButton;
    private Button MealListScreenButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_logged_in_screen);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("accounts");
        userID = user.getUid();

        final TextView userRole = findViewById(R.id.roleCookSpec);


        susCheckRef = reference.child(userID).child("suspension");
        susCheckRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                suspendUpdate((boolean) snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        logOutButton = findViewById(R.id.cookLogOut);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });



        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                Account userProfile = null;
                if (snapshot.getValue(Client.class) != null) {
                    userProfile = snapshot.getValue(Client.class);
                }
                else if (snapshot.getValue(Cook.class) != null) {
                    userProfile = snapshot.getValue(Cook.class);
                }
                else if (snapshot.getValue(Administrator.class) != null){
                    userProfile = snapshot.getValue(Administrator.class);
                }

                if (userProfile != null) {
                    if (userProfile.getType() == AccountType.COOK){
                        userRole.setText("You are signed in as a cook");
                    }
                    else{
                        userRole.setText("Uh Oh! Something went wrong!");
                    }
                }
                else{
                    userRole.setText("Uh Oh! Something went wrong!");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        MenuScreenButton = findViewById(R.id.cookModifyMenu);
        MenuScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMenu();
            }
        });

        MealListScreenButton = findViewById(R.id.cookModifyMealList);
        MealListScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMealList();
            }
        });

    }


    public void suspendUpdate(boolean susCheck){
        DatabaseReference susTimeRef = reference.child(userID).child("suspensionTime");
        susTimeRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Date susTime = snapshot.getValue(Date.class);
                Date currTime = new Date();
                if (susTime != null) {
                    if (susTime.getTime() < currTime.getTime()) {
                        susTimeRef.setValue(new Date(0));
                        susCheckRef.setValue(false);
                    }
                }

                susCheckRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        final TextView susNoti = findViewById(R.id.cookSuspensionNoti);
                        if ((boolean)snapshot.getValue() == true) {
                            susNoti.setText("You are suspended until: " + susTime);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void launchMenu(){
        Intent intent = new Intent(this, CookModifyMenu.class);
        startActivity(intent);
    }

    public void launchMealList(){
        Intent intent = new Intent(this, CookModifyMealList.class);
        startActivity(intent);
    }

    public void logOut(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}