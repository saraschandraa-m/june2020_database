package com.appstone.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mEtPatientName;
    private EditText mEtPatientAge;
    private EditText mEtPatientGender;
    private EditText mEtPatientBloodGroup;
    private EditText mEtPatientHeight;
    private EditText mEtPatientWeight;
    private EditText mEtPatientAppointmentDate;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtPatientName = findViewById(R.id.et_patient_name);
        mEtPatientAge = findViewById(R.id.et_patient_age);
        mEtPatientGender = findViewById(R.id.et_patient_gender);
        mEtPatientBloodGroup = findViewById(R.id.et_patient_blood);
        mEtPatientHeight = findViewById(R.id.et_patient_height);
        mEtPatientWeight = findViewById(R.id.et_patient_weigth);
        mEtPatientAppointmentDate = findViewById(R.id.et_appointment_date);

        dbHelper = new DBHelper(MainActivity.this);


        Button btnPatientAction = findViewById(R.id.btn_add_patient);

        btnPatientAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String patientName = mEtPatientName.getText().toString();
                String patientAge = mEtPatientAge.getText().toString();
                String patientGender = mEtPatientGender.getText().toString();
                String patientBloodGroup = mEtPatientBloodGroup.getText().toString();
                String patientHeight = mEtPatientHeight.getText().toString();
                String patientWeight = mEtPatientWeight.getText().toString();
                String patientAppointDate = mEtPatientAppointmentDate.getText().toString();


                Patient patient = new Patient();

                patient.setPatientName(patientName);
                patient.setPatientAge(patientAge);
                patient.setPatientGender(patientGender);
                patient.setPatientBloodGroup(patientBloodGroup);
                patient.setPatientHeight(patientHeight);
                patient.setPatientWeight(patientWeight);
                patient.setAppointmentDate(patientAppointDate);

                dbHelper.insertDataToDatabase(dbHelper.getWritableDatabase(), patient);

                mEtPatientName.setText("");
                mEtPatientAge.setText("");
                mEtPatientBloodGroup.setText("");
                mEtPatientGender.setText("");
                mEtPatientHeight.setText("");
                mEtPatientWeight.setText("");
                mEtPatientAppointmentDate.setText("");


            }
        });
    }


}