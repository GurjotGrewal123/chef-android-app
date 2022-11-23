package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;

public class AdminLoggedInScreen extends AppCompatActivity {

    Button reviewComplaintsBtn;
    Button logOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_logged_in_screen);
        reviewComplaintsBtn = findViewById(R.id.complaintButton);
        logOutBtn = findViewById(R.id.logOutButton);

        reviewComplaintsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complaintNav();
            }
        });

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOutNav();
            }
        });
    }

    public void complaintNav() {
        Intent intent = new Intent(this, ComplaintsReviewScreen.class);
        this.startActivity(intent);
    }

    public void logOutNav(){
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

}