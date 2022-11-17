package com.example.seg2105project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class CookModifyMenu extends AppCompatActivity {

    private Button AddMealButton;
    private Button MenuHomeButtonNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_modify_menu);

        AddMealButton = findViewById(R.id.addMealtoMenu);
        AddMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddMealToMenuDialog();
            }
        });

        MenuHomeButtonNav = findViewById(R.id.MenuHomeBtn);
        MenuHomeButtonNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navToCookHome();
            }
        });

    }

    public void showAddMealToMenuDialog() {
        AlertDialog.Builder addMealDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_add_menu_item,null);
        addMealDialog.setView(dialogView);

        addMealDialog
                .setCancelable(false)
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        final AlertDialog b = addMealDialog.create();
        b.show();

    }

    public void navToCookHome(){
        Intent intent = new Intent(this, CookLoggedInScreen.class);
        startActivity(intent);
    }

}