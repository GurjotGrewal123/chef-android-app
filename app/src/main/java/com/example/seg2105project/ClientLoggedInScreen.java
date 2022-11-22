package com.example.seg2105project;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClientLoggedInScreen extends AppCompatActivity {

    Button meals;
    Button logO;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_logged_in_screen);

        meals = findViewById(R.id.mealsButton);
        meals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMeals();
            }
        });

        logO = findViewById(R.id.logOutButton);
        logO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });

    }
    public void showMeals(){
        Intent intent = new Intent(this, MenuMealList.class);
        startActivity(intent);
    }
    public void logOut(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
