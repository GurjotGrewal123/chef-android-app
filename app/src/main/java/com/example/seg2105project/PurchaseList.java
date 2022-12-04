package com.example.seg2105project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class PurchaseList extends ArrayAdapter<Purchase> {

    private Activity context;
    List<Purchase> purchases;

    public PurchaseList(Activity context, List<Purchase> purchases){
        super(context, R.layout.activity_purchases_list, purchases);
        this.context = context;
        this.purchases = purchases;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewPurchases = inflater.inflate(R.layout.activity_purchases_list,null,true);

        TextView cookName = (TextView) listViewPurchases.findViewById(R.id.purchaseMealName);
        TextView orderID = (TextView) listViewPurchases.findViewById(R.id.purchaseOrderID);
        TextView purchaseStatus = (TextView) listViewPurchases.findViewById(R.id.purchaseStatus);


        Purchase purchase = purchases.get(position);

        Meal meal = purchase.getMeal();

        cookName.setText("Cook Name: " + meal.getName());
        orderID.setText("Order ID: "+ purchase.getId());
        purchaseStatus.setText("Purchase Status: "+ purchase.getStatus());

        return listViewPurchases;
    }
}
