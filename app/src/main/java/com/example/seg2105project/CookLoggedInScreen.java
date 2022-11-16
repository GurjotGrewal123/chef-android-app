package com.example.seg2105project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CookLoggedInScreen extends AppCompatActivity {

    private Button logOutButton;
    private Button modifyMenu;
    private Button modifyMealList;

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

        modifyMenu = findViewById(R.id.cookModifyMenu);
        modifyMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openModifyMenu();
            }
        });
        modifyMealList = findViewById(R.id.cookModifyMealList);
        modifyMealList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMealList();
            }
        });

    }
    public void logOut(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openModifyMenu(){
        Intent intent = new Intent(this, CookModifyMenu.class);
        startActivity(intent);
    }
    public void openMealList(){
        Intent intent = new Intent(this, CookModifyMealList.class);
        startActivity(intent);
    }
}