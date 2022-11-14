package com.example.seg2105project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import { getAuth, deleteUser } from "firebase/auth";
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ComplaintsReviewScreen extends AppCompatActivity {

    DatabaseReference reference;
    DatabaseReference accountRef;
    ListView complaints;
    List<Complaint> complaintsList;
    ArrayAdapter arrayAdapter;
    DatabaseReference complaintDB;
    Button insertData;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_review_screen);
        reference = FirebaseDatabase.getInstance().getReference("complaints");
        mAuth = FirebaseAuth.getInstance();

        System.out.println(reference.child("user"));
        accountRef = FirebaseDatabase.getInstance().getReference("accounts");
        complaints = findViewById(R.id.complaintsList);
        complaintsList = new ArrayList<>();
        onItemLongClick();

        insertData = findViewById(R.id.insertDataComplaint);
        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertComplaint();
            }
        });

    }

    protected void onStart(){
        super.onStart();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                complaintsList.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    //key = postSnapshot.getKey();
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

                accountRef.child(complaint.getCook()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Cook userProfile = snapshot.getValue(Cook.class);
                        showComplaintDashboardDialog(userProfile.getName(), complaint.getDate().toString(), complaint.getCook());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                return true;
            }
        });
    }

    private void showComplaintDashboardDialog(final String cookname, final String date, final String cookID){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_complaint_pop_up,null);
        dialogBuilder.setView(dialogView);

        dialogBuilder
                .setCancelable(false)
                .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        final TextView cookName = (TextView) dialogView.findViewById(R.id.nameOfCook);
        final TextView complaintText = (TextView) dialogView.findViewById(R.id.complaintForCook);
        final TextView dateOfComplaint = (TextView) dialogView.findViewById(R.id.dateComplainedAbout);
        final TextView adminPrompt = (TextView) dialogView.findViewById(R.id.adminPrompt);
        final Button permenantlySuspend = (Button) dialogView.findViewById(R.id.permaSuspend);
        final Button temporarilySuspend = (Button) dialogView.findViewById(R.id.tempSuspend);
        final Button dismissComplaint = (Button) dialogView.findViewById(R.id.dissmissComplaint);

        cookName.setText("Cook's Name: " + cookname);
        complaintText.setText("This was issued on: "+ date);
        final AlertDialog b = dialogBuilder.create();
        b.show();
        permenantlySuspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pSuspend();

            }
        });
        temporarilySuspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        tSuspend(cookID);
                    }
        });
        dismissComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disCom();
            }
        });
    }
    public void pSuspend(){
        Toast.makeText(ComplaintsReviewScreen.this, "Permanently Suspended Cook" , Toast.LENGTH_LONG).show();

    }
    public void tSuspend(final String cookID){
        accountRef.child(cookID).child("suspension").setValue(true);
        Date currTime = new Date();
        accountRef.child(cookID).child("suspensionTime").setValue(new Date(currTime.getTime() + 60000*5));
        Toast.makeText(ComplaintsReviewScreen.this, "Temporarily Suspended Cook" , Toast.LENGTH_LONG).show();
    }
    public void disCom(){
        Toast.makeText(ComplaintsReviewScreen.this, "Complaint Dismissed" , Toast.LENGTH_LONG).show();

    }


    public void insertComplaint(){
        String summary = "This isc  the first complaint";
        String userID = "MN3GZyMUG7Rmz3k9oJ5e5nYgGd13";
        String cookID = "N1J77V5xAKeDOe5DVLETk6GUa8p2";
        String id = reference.push().getKey();

        Complaint complaint = new Complaint(summary, userID, cookID, id);
        reference.child(id).setValue(complaint);
    }

}