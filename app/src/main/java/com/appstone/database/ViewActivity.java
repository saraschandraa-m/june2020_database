package com.appstone.database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity implements PatientInfoAdapter.PatientClickListener {


    private DBHelper dbHelper;
    private RecyclerView mRcPatientInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        mRcPatientInfos = findViewById(R.id.rc_patient_info);
        mRcPatientInfos.setLayoutManager(new LinearLayoutManager(ViewActivity.this, RecyclerView.VERTICAL, false));

//        mRcPatientInfos.setLayoutManager(new GridLayoutManager(ViewActivity.this, 3));
//        mRcPatientInfos.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        //Linear
        //Grid
        //Staggered

//        button.setOnClickListener(this);

        dbHelper = new DBHelper(ViewActivity.this);

        loadDataToRecycler();
    }

    @Override
    public void onUpdateClicked(Patient patient) {
        Intent updateIntent = new Intent(ViewActivity.this, MainActivity.class);
        updateIntent.putExtra("PATIENT", patient);
        startActivityForResult(updateIntent, 1001);
    }

    @Override
    public void onDeleteClicked(Patient patient) {
        dbHelper.deleteDataFromDatabase(dbHelper.getWritableDatabase(), patient);
        loadDataToRecycler();
    }

    private void loadDataToRecycler() {
        ArrayList<Patient> patientDetails = dbHelper.getDataFromDatabase(dbHelper.getReadableDatabase());

        PatientInfoAdapter adapter = new PatientInfoAdapter(ViewActivity.this, patientDetails);
        adapter.setListener(this);
        mRcPatientInfos.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            if (resultCode == Activity.RESULT_OK) {
                loadDataToRecycler();
            }
        }
    }
}