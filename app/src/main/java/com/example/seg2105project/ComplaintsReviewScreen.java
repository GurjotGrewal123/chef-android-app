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

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints_review_screen);
        reference = FirebaseDatabase.getInstance().getReference("complaints");
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
                        showComplaintDashboardDialog(userProfile.getName(),complaint.getDate().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                return true;
            }
        });
    }

    private void showComplaintDashboardDialog(final String cookname, final String date){
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

        cookName.setText(cookname);
        complaintText.setText(date);
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
                        tSuspend();
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

    }
    public void tSuspend(){

    }
    public void disCom(){

    }


    public void insertComplaint(){
        String summary = "This is the first complaint";
        String userID = "1FRyh9AxIWQgZACNrwKHSraga962";
        String cookID = "N1J77V5xAKeDOe5DVLETk6GUa8p2";

        Complaint complaint = new Complaint(summary, userID, cookID);
        reference.push().setValue(complaint);
    }

}