package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;

public class complaintsReviewScreen extends AppCompatActivity {

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_review_screen);
       // reference = FirebaseDatabase.getInstance().getReference("Complaints");

    }
}