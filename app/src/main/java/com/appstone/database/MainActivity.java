package com.appstone.database;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText mEtPatientName;
    private EditText mEtPatientAge;
    private EditText mEtPatientGender;
    private EditText mEtPatientBloodGroup;
    private EditText mEtPatientHeight;
    private EditText mEtPatientWeight;


    private TextView mEtPatientAppointmentDate;

    private DBHelper dbHelper;

    private int patientID;

    private boolean isUpdate;

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

        Bundle data = getIntent().getExtras();
        if (data != null) {
            Patient patient = (Patient) data.getSerializable("PATIENT");
            isUpdate = true;

            mEtPatientName.setText(patient.getPatientName());
            mEtPatientAge.setText(patient.getPatientAge());
            mEtPatientGender.setText(patient.getPatientGender());
            mEtPatientBloodGroup.setText(patient.getPatientBloodGroup());
            mEtPatientHeight.setText(patient.getPatientHeight());
            mEtPatientWeight.setText(patient.getPatientWeight());
            mEtPatientAppointmentDate.setText(patient.getAppointmentDate());

            patientID = patient.getPatientID();
        }


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
                if (isUpdate) {
                    patient.setPatientID(patientID);
                    dbHelper.updateDataToDatabase(dbHelper.getWritableDatabase(), patient);
                    setResult(Activity.RESULT_OK);
                    finish();
                } else {
                    dbHelper.insertDataToDatabase(dbHelper.getWritableDatabase(), patient);
                }

                mEtPatientName.setText("");
                mEtPatientAge.setText("");
                mEtPatientBloodGroup.setText("");
                mEtPatientGender.setText("");
                mEtPatientHeight.setText("");
                mEtPatientWeight.setText("");
                mEtPatientAppointmentDate.setText("");


            }
        });

        Button btnGetInfo = findViewById(R.id.btn_get_patient_info);

        btnGetInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Patient> patientList = dbHelper.getDataFromDatabase(dbHelper.getReadableDatabase());


                Toast.makeText(MainActivity.this, "Name : " + patientList.get(0).getPatientName(), Toast.LENGTH_LONG).show();
            }
        });

        mEtPatientAppointmentDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });
    }

    private void openDatePicker() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            DatePickerDialog datePickerDialog = null;
//
//            datePickerDialog = new DatePickerDialog(MainActivity.this);
//
//            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                    String date = String.valueOf(day).concat("-").concat(String.valueOf(month + 1)).concat("-").concat(String.valueOf(year));
//                    mEtPatientAppointmentDate.setText(date);
//                }
//            });
//
//            datePickerDialog.show();


            TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hours, int min) {
                    String duration = "";
                    int hourvalue;
                    if (hours < 12) {
                        hourvalue = hours;
                        duration = "AM";
                    } else if (hours == 12) {
                        hourvalue = hours;
                        duration = "PM";
                    } else {
                        hourvalue = hours % 12;
                        duration = "PM";
                    }
                    String date = String.valueOf(hourvalue).concat(":").concat(String.valueOf(min)).concat(" ").concat(duration);
//                    mEtPatientAppointmentDate.setText(date);
                }
            }, Calendar.HOUR_OF_DAY, Calendar.MINUTE, false);
            timePickerDialog.show();


            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa", Locale.ENGLISH);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM YYYY");
            String currenTime = timeFormat.format(calendar.getTime());
            String currentDate = dateFormat.format(calendar.getTime());
            mEtPatientAppointmentDate.setText(currentDate + " - " + currenTime);


        }
    }


}