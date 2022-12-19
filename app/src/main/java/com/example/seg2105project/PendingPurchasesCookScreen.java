package com.example.seg2105project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PendingPurchasesCookScreen extends AppCompatActivity {

    DatabaseReference reference;
    DatabaseReference accountRef;
    ListView purchases;
    List<Purchase> purchasesList;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_purchases_cook_screen);

        mAuth = FirebaseAuth.getInstance();
        accountRef = FirebaseDatabase.getInstance().getReference("accounts");
        reference = accountRef.child(mAuth.getUid()).child("yourPurchaseRequests");

        purchases = findViewById(R.id.pendingPurchasesCook);
        purchasesList = new ArrayList<>();
        onItemLongClick();
    }

    protected void onStart(){
        super.onStart();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                purchasesList.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){

                    Purchase purchase = postSnapshot.getValue(Purchase.class);
                    System.out.println(purchase.getId());
                    purchasesList.add(purchase);
                }
                PurchaseList purchaseAdapter = new PurchaseList(PendingPurchasesCookScreen.this, purchasesList);
                purchases.setAdapter(purchaseAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void onItemLongClick(){
        purchases.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Purchase purchase = purchasesList.get(i);
                accountRef.child(purchase.getClientID()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Client client = snapshot.getValue(Client.class);
                        showModifyCurrentPurchaseDialog(purchase, client);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                return true;
            }
        });
    }

    private void showModifyCurrentPurchaseDialog(Purchase purchase, Client client){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.preview_purchase_request_dialog,null);
        dialogBuilder.setView(dialogView);

        final TextView clientName = dialogView.findViewById(R.id.purchaseRequestUser);
        final TextView mealName = dialogView.findViewById(R.id.purchaseRequestMeal);
        final TextView mealPrice = dialogView.findViewById(R.id.purchaseRequestPrice);
        final TextView orderID = dialogView.findViewById(R.id.purchaseRequestID);

        final Button acceptBtn = dialogView.findViewById(R.id.acceptPurchase);
        final Button refuseBtn = dialogView.findViewById(R.id.refusePurchase);

        clientName.setText("Client: "+ client.getName());
        mealName.setText("Meal Name: "+ purchase.getMeal().getName());
        mealPrice.setText("Price: "+purchase.getMeal().getPrice());
        orderID.setText("Order ID: "+purchase.getId());

        final AlertDialog b = dialogBuilder.create();
        b.show();

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child(purchase.getId()).child("status").setValue("approved");
                accountRef.child(purchase.getClientID()).child("yourOrders").child(purchase.getId()).child("status").setValue("approved");
                b.dismiss();
            }
        });

        refuseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child(purchase.getId()).removeValue();
                accountRef.child(purchase.getClientID()).child("yourOrders").child("status").setValue("rejected");
                b.dismiss();
            }
        });
    }

    }