package com.example.seg2105project;

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

public class CookSignUpScreen extends AppCompatActivity {

    private Button homeBtn;
    private Button registerCookBtn;

    EditText cookFirstName;
    EditText cookLastName;
    EditText password;
    EditText email;
    EditText username;

    EditText postal;
    EditText street;
    EditText city;
    EditText country;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_sign_up_screen);

        homeBtn = findViewById(R.id.homeButton);
        mAuth = FirebaseAuth.getInstance();
        registerCookBtn = findViewById(R.id.registerationCompleteButton);
        cookFirstName = findViewById(R.id.inputNameCook);
        cookLastName = findViewById(R.id.inputSurnameCook);
        password = findViewById(R.id.inputPasswordCook);
        email = findViewById(R.id.inputEmailCook);
        postal = findViewById(R.id.inputPostalAddressCook);
        street = findViewById(R.id.inputStreetAddressCook);
        city = findViewById(R.id.inputCityAddressCook);
        country = findViewById(R.id.inputCountryAddressCook);
        username = findViewById(R.id.inputUsernameCook);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeNavigator();
            }
        });

        registerCookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerCookBtn();
            }
        });
    }

    public void homeNavigator(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void registerCookBtn(){
        String emailReg = email.getText().toString();
        String name = cookFirstName.getText().toString() +" "+ cookLastName.getText().toString();
        Address address = new Address(street.getText().toString(), city.getText().toString(), country.getText().toString(), postal.getText().toString());
        String user = username.getText().toString();
        String pass = password.getText().toString();

        if(cookFirstName.getText().toString().isEmpty()){
            cookFirstName.setError("Full name is required");
            cookFirstName.requestFocus();
            return;
        }
        if(cookLastName.getText().toString().isEmpty()){
            cookLastName.setError("Last name is required");
            cookLastName.requestFocus();
            return;
        }
        if(username.getText().toString().isEmpty()){
            username.setError("Username name is required");
            username.requestFocus();
            return;
        }
        if(password.getText().toString().isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if(pass.length() < 6){
            password.setError("Password needs to be longer than 6 characters");
            password.requestFocus();
            return;
        }
        if(email.getText().toString().isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if(!(emailReg).matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            street.setError("Not a valid email type eg. user_name@gmail.com");
            street.requestFocus();
            return;
        }
        if(street.getText().toString().isEmpty()){
            street.setError("Street name is required");
            street.requestFocus();
            return;
        }
        if(city.getText().toString().isEmpty()){
            city.setError("City name is required");
            city.requestFocus();
            return;
        }
        if(country.getText().toString().isEmpty()){
            country.setError("Country name is required");
            country.requestFocus();
            return;
        }
        if(postal.getText().toString().isEmpty()){
            postal.setError("Postal code is required");
            postal.requestFocus();
            return;
        }


        Cook cook = new Cook(emailReg, user, pass, name, address);

        mAuth.createUserWithEmailAndPassword(emailReg, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Cook cook = new Cook(emailReg, user, pass, name, address);

                    FirebaseDatabase.getInstance().getReference("accounts")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(cook).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(CookSignUpScreen.this, "Congrats! You have been registered. Please go to the home screen and login.", Toast.LENGTH_SHORT).show();
                                        textClear();
                                    }
                                }
                            });
                }
            }
        });
    }

    public void textClear(){
        cookFirstName.getText().clear();
        cookLastName.getText().clear();
        street.getText().clear();
        city.getText().clear();
        country.getText().clear();
        postal.getText().clear();
        username.getText().clear();
        password.getText().clear();
    }
}