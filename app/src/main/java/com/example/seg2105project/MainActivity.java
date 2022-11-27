package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import android.text.TextUtils;
import android.util.Patterns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.TextView;

import java.security.cert.PolicyNode;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText pass;

    Button cookRegisterBtn;
    Button clientRegisterBtn;
    Button loginBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.emailInputField);
        pass = findViewById(R.id.passwordInputField);

        cookRegisterBtn = findViewById(R.id.registerCookButton);
        cookRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cookRegisterNavigator();
            }
        });

        clientRegisterBtn= findViewById(R.id.registerClientButton);
        clientRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clientRegisterNavigator();
            }
        });

        loginBtn = findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    public void cookRegisterNavigator() {
        Intent intent = new Intent(this, CookSignUpScreen.class);
        startActivity(intent);
    }

    public void clientRegisterNavigator() {
        Intent intent = new Intent(this, ClientSignUpScreen.class);
        startActivity(intent);
    }
    public void login(){
        Intent intent = new Intent(this, LoggedInScreen.class);
        startActivity(intent);
    }

    public void loginCook(){
        Intent intent = new Intent(this, CookLoggedInScreen.class);
        startActivity(intent);
    }

    public void launchAdmin(){
        Intent intent = new Intent(this, AdminLoggedInScreen.class);
        startActivity(intent);
    }


    public void loginUser(){
        String emailLog = email.getText().toString();
        String password = pass.getText().toString();

        if(email.getText().toString().isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if(!(emailLog).matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            email.setError("Not a valid email type eg. user_name@gmail.com");
            email.requestFocus();
            return;
        }
        if(pass.getText().toString().isEmpty()){
            pass.setError("Password is required");
            pass.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(emailLog, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    String userID = mAuth.getUid();
                    FirebaseDatabase.getInstance().getReference("accounts").child(userID).child("type").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String currUser = (String) snapshot.getValue();
                            if(currUser.equals("ADMIN")){
                                launchAdmin();
                            }else if (currUser.equals("COOK")){
                                loginCook();
                            }
                            else if (currUser.equals("CLIENT")){
                                //setContentView(R.layout.activity_client_logged_in_screen);
                                login();
                            }
                            else{
                                login();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this, "Wrong credentials. Input correct login info.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}