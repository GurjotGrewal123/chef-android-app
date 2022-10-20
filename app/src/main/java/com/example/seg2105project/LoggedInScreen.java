package com.example.seg2105project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoggedInScreen extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in_screen);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("accounts");
        userID = user.getUid();

        final TextView userRole = findViewById(R.id.roleSpecifier);

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
                else{

                }

                if (userProfile != null) {
                    if (userProfile.getType() == AccountType.CLIENT){
                        userRole.setText("You are signed in as a client");
                    }
                    else if (userProfile.getType() == AccountType.COOK){
                        userRole.setText("You are signed in as a cook");
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
    }
}