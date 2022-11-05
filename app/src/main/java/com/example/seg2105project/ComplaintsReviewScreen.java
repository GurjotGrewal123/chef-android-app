package com.example.seg2105project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComplaintsReviewScreen extends AppCompatActivity {

    DatabaseReference reference;
    ListView complaints;
    List<Complaint> complaintsList;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_review_screen);
        reference = FirebaseDatabase.getInstance().getReference("complaints");

        complaintsList = new ArrayList<>();

        //TO BE IMPLEMENTED
        addComplaint();
        onItemLongClick();

    }

    protected void onStart(){
        super.onStart();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                complaintsList.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Complaint complaint = postSnapshot.getValue(Complaint.class);
                    complaintsList.add(complaint);
                }
                ComplaintList complaintAdapter = new ComplaintList(ComplaintsReviewScreen.this, complaintsList);
                complaints.setAdapter(complaintAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}