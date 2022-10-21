package com.example.seg2105project;

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

public class ClientSignUpScreen extends AppCompatActivity {
    Button homeBtn;
    Button registerClientBtn;
    EditText clientFirstName;
    EditText clientLastName;
    EditText password;
    EditText email;
    EditText username;

    EditText postal;
    EditText street;
    EditText city;
    EditText country;

    EditText cardNum;
    EditText securityCode;
    EditText exp;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        mAuth = FirebaseAuth.getInstance();

        homeBtn = findViewById(R.id.homeBtn2);
        registerClientBtn = findViewById(R.id.registerationCompleteButton);
        clientFirstName = findViewById(R.id.inputNameClient);
        clientLastName = findViewById(R.id.inputSurnameClient);
        password = findViewById(R.id.inputPasswordClient);
        email = findViewById(R.id.inputEmailClient);
        postal = findViewById(R.id.inputPostalAddressClient);
        street = findViewById(R.id.inputStreetAddressClient);
        city = findViewById(R.id.inputCityAddressClient);
        country = findViewById(R.id.inputCountryAddressClient);
        username = findViewById(R.id.inputUsernameClient);
        cardNum = findViewById(R.id.inputCardCVV);
        securityCode = findViewById(R.id.enterCardDate);
        exp = findViewById(R.id.inputCardNumber);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeNavigator();
            }
        });

        registerClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerClientData();
            }
        });

    }

    public void homeNavigator(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void registerClientData(){
        String emailReg = email.getText().toString();
        String name = clientFirstName.getText().toString() +" "+ clientLastName.getText().toString();
        Address address = new Address(street.getText().toString(), city.getText().toString(), country.getText().toString(), postal.getText().toString());
        String user = username.getText().toString();
        String pass = password.getText().toString();
        Card card = new Card(name, cardNum.getText().toString(), securityCode.getText().toString(), exp.getText().toString());

        if(clientFirstName.getText().toString().isEmpty()){
            clientFirstName.setError("Full name is required");
            clientFirstName.requestFocus();
            return;
        }
        if(clientLastName.getText().toString().isEmpty()){
            clientLastName.setError("Last name is required");
            clientLastName.requestFocus();
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
        if(cardNum.getText().toString().isEmpty()){
            cardNum.setError("Card number is required");
            cardNum.requestFocus();
            return;
        }
        if(exp.getText().toString().isEmpty()){
            exp.setError("Expiry date is required");
            exp.requestFocus();
            return;
        }
        if(securityCode.getText().toString().isEmpty()){
            securityCode.setError("CVV code is required");
            securityCode.requestFocus();
            return;
        }


        Client client = new Client(emailReg, user, pass, name, address, card);

        mAuth.createUserWithEmailAndPassword(emailReg, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseDatabase.getInstance().getReference("accounts")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(client).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(ClientSignUpScreen.this, "Congrats! You have been registered. Please go to the home screen and login.", Toast.LENGTH_SHORT).show();
                                        textClear();
                                    }
                                }
                            });
                }
            }
        });

    }


    public void textClear(){
        clientLastName.getText().clear();
        clientFirstName.getText().clear();
        street.getText().clear();
        city.getText().clear();
        country.getText().clear();
        postal.getText().clear();
        username.getText().clear();
        password.getText().clear();
        email.getText().clear();
    }

}