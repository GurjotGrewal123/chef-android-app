package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CookLoggedInScreen extends AppCompatActivity {

    private Button logOutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_logged_in_screen);

        logOutButton = findViewById(R.id.cookLogOut);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
    }
    public void logOut(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}