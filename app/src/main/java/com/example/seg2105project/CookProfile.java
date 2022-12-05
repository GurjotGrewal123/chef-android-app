package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

public class CookProfile extends AppCompatActivity {

    TextView cookRating;
    TextView cookIntro;
    TextView cookAdress;
    TextView profileName;
    private FirebaseUser user;
    private DatabaseReference ref;
    private String userID;
    int topFrac;
    int bottomFrac;
    int n;
    public String city;
    private String country;
    private String postal;
    private String street;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_profile);

        user = FirebaseAuth.getInstance().getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("accounts");
        userID = user.getUid();

        cookRating = findViewById(R.id.cookProfileRating);
        cookIntro = findViewById(R.id.cookProfileIntro);
        cookAdress = findViewById(R.id.cookAddress);
        profileName = findViewById(R.id.profileNameIntro);


        ref.child(userID).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue(String.class);
                profileName.setText("Cook: " + s);
                //do what you want with the email
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.child(userID).child("rating").child("totalRaters").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int n = dataSnapshot.getValue(Integer.class);
                topFrac = n;
                cookRating.setText("Ratings: " + topFrac + "/" + bottomFrac);
                //do what you want with the email
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child(userID).child("rating").child("totalRatings").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int n = dataSnapshot.getValue(Integer.class);
                bottomFrac = n;
                cookRating.setText("Ratings: " + topFrac + "/" + bottomFrac);
                //do what you want with the email
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.child(userID).child("suspension").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean b = dataSnapshot.getValue(boolean.class);
                cookIntro.setText("Suspension: " + b );

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ref.child(userID).child("address").child("city").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String v = dataSnapshot.getValue(String.class);
                city = v;
                cookAdress.setText("Address: " + city + ", " + country + ", " + postal + ", " + street);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child(userID).child("address").child("country").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue(String.class);
                country = s;
                cookAdress.setText("Address: " + city + ", " + country + ", " + postal + ", " + street);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child(userID).child("address").child("postalCode").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue(String.class);
                postal = s;
                cookAdress.setText("Address: " + city + ", " + country + ", " + postal + ", " + street);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref.child(userID).child("address").child("street").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String s = dataSnapshot.getValue(String.class);
                street = s;
                cookAdress.setText("Address: " + city + ", " + country + ", " + postal + ", " + street);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        cookRating.setText("Ratings: " + topFrac + "/" + bottomFrac);
        //cookAdress.setText("Address: " + city + ", " + country + ", " + postal + ", " + street);

    }




}