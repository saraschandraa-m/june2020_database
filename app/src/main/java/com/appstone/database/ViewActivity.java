package com.appstone.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        RecyclerView mRcPatientInfos = findViewById(R.id.rc_patient_info);
        mRcPatientInfos.setLayoutManager(new LinearLayoutManager(ViewActivity.this, RecyclerView.VERTICAL, false));

//        mRcPatientInfos.setLayoutManager(new GridLayoutManager(ViewActivity.this, 3));
//        mRcPatientInfos.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        //Linear
        //Grid
        //Staggered

        DBHelper dbHelper = new DBHelper(ViewActivity.this);
        ArrayList<Patient> patientDetails = dbHelper.getDataFromDatabase(dbHelper.getReadableDatabase());

        PatientInfoAdapter adapter = new PatientInfoAdapter(ViewActivity.this, patientDetails);

        mRcPatientInfos.setAdapter(adapter);
    }
}