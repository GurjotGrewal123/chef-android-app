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

public class ComplaintList extends ArrayAdapter<Complaint> {

    DatabaseReference accountRef = FirebaseDatabase.getInstance().getReference("accounts");
    private Activity context;
    List<Complaint> complaints;

    public ComplaintList(Activity context, List<Complaint> complaints){
        super(context, R.layout.activity_complaint_list, complaints);
        this.context = context;
        this.complaints = complaints;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewComplaint = inflater.inflate(R.layout.activity_complaint_list,null,true);

        TextView cookName = (TextView) listViewComplaint.findViewById(R.id.cookName);
        TextView dateComplaint = (TextView) listViewComplaint.findViewById(R.id.dateSubmittedComplaint);

        Complaint complaint = complaints.get(position);

        accountRef.child(complaint.getCook()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Cook userProfile = snapshot.getValue(Cook.class);
                cookName.setText("Cook: " + userProfile.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dateComplaint.setText("Date Submitted: "+complaint.getDate());
        return listViewComplaint;
    }
}
