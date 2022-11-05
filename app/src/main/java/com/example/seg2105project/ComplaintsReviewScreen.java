package com.example.seg2105project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
        onItemLongClick();

    }

    protected void onStart(){
        super.onStart();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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

    private void onItemLongClick(){
        complaints.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Complaint complaint = complaintsList.get(i);
                showComplaintDashboardDialog(complaint.getCook().getName(),complaint.getDate().toString());
                return true;
            }
        });
    }

    private void showComplaintDashboardDialog(final String cookname, final String date){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_complaint_pop_up,null);
        dialogBuilder.setView(dialogView);

        final TextView cookName = (TextView) dialogView.findViewById(R.id.nameOfCook);
        final TextView complaintText = (TextView) dialogView.findViewById(R.id.complaintForCook);
        final TextView dateOfComplaint = (TextView) dialogView.findViewById(R.id.dateComplainedAbout);
        final TextView adminPrompt = (TextView) dialogView.findViewById(R.id.adminPrompt);
        final Button permenantlySuspend = (Button) dialogView.findViewById(R.id.permaSuspend);
        final Button temporarilySuspend = (Button) dialogView.findViewById(R.id.tempSuspend);
        final Button dismissComplaint = (Button) dialogView.findViewById(R.id.dissmissComplaint);
        final Button cancelButton = (Button) dialogView.findViewById(R.id.returnButton);

        cookName.setText(cookname);
        complaintText.setText(date);
        final AlertDialog b = dialogBuilder.create();
        b.show();

    }

}