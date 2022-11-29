package com.example.seg2105project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private Button checkPendingPurchases;
    private Button checkProfile;

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


        MenuScreenButton = findViewById(R.id.cookModifyMenu);
        MenuScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                susCheckRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if ((boolean)snapshot.getValue() == true) {
                            Toast.makeText(CookLoggedInScreen.this, "Sorry, you're restricted from doing this because you're suspended!" , Toast.LENGTH_LONG).show();
                        }
                        else{
                            launchMenu();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        MealListScreenButton = findViewById(R.id.cookModifyMealList);
        MealListScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                susCheckRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if ((boolean)snapshot.getValue() == true) {
                            Toast.makeText(CookLoggedInScreen.this, "Sorry, you're restricted from doing this because you're suspended!" , Toast.LENGTH_LONG).show();
                        }
                        else{
                            launchMealList();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        checkPendingPurchases = findViewById(R.id.cookPendingPurchases);
        checkPendingPurchases.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                susCheckRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if ((boolean)snapshot.getValue() == true) {
                            Toast.makeText(CookLoggedInScreen.this, "Sorry, you're restricted from doing this because you're suspended!" , Toast.LENGTH_LONG).show();
                        }
                        else{
                            navToPendingPurchases();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }));

        checkProfile= findViewById(R.id.cookCheckProfile);
        checkProfile.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                susCheckRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if ((boolean)snapshot.getValue() == true) {
                            Toast.makeText(CookLoggedInScreen.this, "Sorry, you're restricted from doing this because you're suspended!" , Toast.LENGTH_LONG).show();
                        }
                        else{
                            navTocheckProfile();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }));


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

    public void navToPendingPurchases(){
        Intent intent = new Intent(this, PendingPurchasesCookScreen.class);
        startActivity(intent);
    }

    public void navTocheckProfile(){
        Intent intent = new Intent(this, CookProfile.class);
        startActivity(intent);
    }


}