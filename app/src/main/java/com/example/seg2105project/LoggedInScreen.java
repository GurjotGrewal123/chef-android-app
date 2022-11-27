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

/**
 * this class if for the main log in screen. all users will see this screen and then be redirected according to their account type.
 *
 */
public class LoggedInScreen extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private Button logOutButton;

    private Button menuButton;
    private Button viewCooks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in_screen);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("accounts");
        userID = user.getUid();

        final TextView userRole = findViewById(R.id.roleSpecifier);

        logOutButton = findViewById(R.id.logOutButton);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });

        menuButton = findViewById(R.id.browseMeals);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu();
            }
        });

        viewCooks = findViewById(R.id.cooks);
        viewCooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCooks();
            }
        });



    }



    public void logOut(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openMenu(){
        Intent intent = new Intent(this, MealSearchParamaterScreen.class);
        startActivity(intent);
    }
    public void openCooks(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}