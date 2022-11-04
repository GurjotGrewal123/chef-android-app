package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import android.os.Bundle;

public class AdminLoggedInScreen extends AppCompatActivity {

    Button reviewComplaintsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_logged_in_screen);
        reviewComplaintsBtn = (Button) findViewById(R.id.complaintsButton);
        reviewComplaintsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                complaintNav();

            }
        });

    }
//setContentView(R.layout.activity_logged_in_screen);
    public void complaintNav() {
        Intent intent = new Intent(this, complaintsReviewScreen.class);
        startActivity(intent);


    }
}