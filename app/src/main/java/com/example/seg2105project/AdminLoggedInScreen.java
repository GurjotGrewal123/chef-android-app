package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;

public class AdminLoggedInScreen extends AppCompatActivity {

    Button reviewComplaintsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_logged_in_screen);
        reviewComplaintsBtn = findViewById(R.id.complaintButton);

        reviewComplaintsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complaintNav();
            }
        });


    }

    public void complaintNav() {
        Intent intent = new Intent(this, ComplaintsReviewScreen.class);
        this.startActivity(intent);
    }

}