package com.example.seg2105project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class ComplaintList extends ArrayAdapter<Complaint> {

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
        cookName.setText("Cook: "+complaint.getCook());
        dateComplaint.setText("Date Submitted: "+complaint.getDate());
        return listViewComplaint;
    }
}
