package com.example.seg2105project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CustomerPreviousOrders extends AppCompatActivity {

    DatabaseReference reference;
    DatabaseReference accountRef;
    DatabaseReference complaintRef;
    ListView purchases;
    List<Purchase> purchasesList;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_previous_orders);

        mAuth = FirebaseAuth.getInstance();
        accountRef = FirebaseDatabase.getInstance().getReference("accounts");
        reference = accountRef.child(mAuth.getUid()).child("yourOrders");
        complaintRef = FirebaseDatabase.getInstance().getReference("complaints");

        purchases = findViewById(R.id.orders);
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
                    purchasesList.add(purchase);
                }
                PurchaseList purchaseAdapter = new PurchaseList(CustomerPreviousOrders.this, purchasesList);
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
                accountRef.child(purchase.getMeal().getCookAssignedID()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Cook cook = snapshot.getValue(Cook.class);
                        showModifyCurrentOrderDialog(purchase, cook);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                return true;
            }
        });
    }

    private void showModifyCurrentOrderDialog(Purchase purchase, Cook cook){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_previous_order_item_preview_screen,null);
        dialogBuilder.setView(dialogView);

        final TextView mealName = dialogView.findViewById(R.id.previewMealName);
        final TextView mealPrice = dialogView.findViewById(R.id.previewMealPrice);
        final TextView cookName = dialogView.findViewById(R.id.previewMealCook);
        final TextView cookRating = dialogView.findViewById(R.id.previewMealCookRating);
        final TextView status = dialogView.findViewById(R.id.previewMealStatus);

        final Button rateCookBtn = dialogView.findViewById(R.id.rateCook);
        final Button enterComplaintBtn = dialogView.findViewById(R.id.complainAboutCook);
        final Button cancelBtn = dialogView.findViewById(R.id.cancelButton);


        mealName.setText("Meal Name: "+ purchase.getMeal().getName());
        mealPrice.setText("Price: "+purchase.getMeal().getPrice());
        cookName.setText("Cook Name: "+cook.getName());
        cookRating.setText("Rating: "+cook.getRating().getRating());
        status.setText("Purchase Status: "+purchase.getStatus());

        final AlertDialog b = dialogBuilder.create();
        b.show();

        rateCookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rateCookDialog(purchase);
                b.dismiss();
            }
        });

        enterComplaintBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterComplaint(purchase);
                b.dismiss();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });
    }

    private void enterComplaint(Purchase purchase){
        String id = complaintRef.push().getKey();
        Complaint complaint = new Complaint("placeholder", mAuth.getUid(), purchase.getMeal().getCookAssignedID(), id);
        complaintRef.child(id).setValue(complaint);
        Toast.makeText(CustomerPreviousOrders.this, "Your complaint has been sent" , Toast.LENGTH_LONG).show();
    }

    private void rateCookDialog(Purchase purchase){
        AlertDialog.Builder dialogBuilderRating = new AlertDialog.Builder(this);
        LayoutInflater inflaterRating = getLayoutInflater();
        final View dialogViewRating = inflaterRating.inflate(R.layout.activity_rate_cook_screen,null);
        dialogBuilderRating.setView(dialogViewRating);

        final TextView rating = dialogViewRating.findViewById(R.id.rateCookInput);
        final Button submitButton = dialogViewRating.findViewById(R.id.submitRatingButton);
        final Button cancelButton = dialogViewRating.findViewById(R.id.cancelRating);

        final AlertDialog c = dialogBuilderRating.create();
        c.show();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitRating(purchase, rating);
                c.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.dismiss();
            }
        });
    }

    private void submitRating(Purchase purchase, TextView rating){
        accountRef.child(purchase.getMeal().getCookAssignedID()).child("rating").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (rating.getText().toString().isEmpty()){
                    Toast.makeText(CustomerPreviousOrders.this, "Please enter a number", Toast.LENGTH_LONG).show();
                }
                else {
                    Rating addRating = snapshot.getValue(Rating.class);
                    addRating.setTotalRatings(Integer.parseInt(rating.getText().toString()));
                    addRating.setTotalRaters();

                    accountRef.child(purchase.getMeal().getCookAssignedID()).child("rating").setValue(addRating);
                    Toast.makeText(CustomerPreviousOrders.this, "You have rated the cook a " + rating.getText().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}