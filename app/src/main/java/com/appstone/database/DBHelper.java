package com.appstone.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "patient_info_table";
    private static final String COL_ID = "id";
    private static final String COL_PATIENT_NAME = "patient_name";
    private static final String COL_PATIENT_AGE = "patient_age";
    private static final String COL_PATIENT_BLOODGROUP = "patient_blood_group";
    private static final String COL_PATIENT_GENDER = "patient_gender";
    private static final String COL_PATIENT_HEIGHT = "patient_height";
    private static final String COL_PATIENT_WEIGHT = "patient_weight";
    private static final String COL_PATIENT_APPOINTMENT_DATE = "patient_appointment_date";


    // CREATE TABLE patient_info_table(id INTEGER PRIMARY KEY AUTOINCREMENT,patient_name TEXT,patient_age TEXT,patient_gender TEXT,patient_height TEXT,
    // patient_weight TEXT, patient_appointment_date TEXT)


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_PATIENT_NAME +
            " TEXT," + COL_PATIENT_AGE + " TEXT," + COL_PATIENT_GENDER + " TEXT," + COL_PATIENT_BLOODGROUP + " TEXT," +
            COL_PATIENT_HEIGHT + " TEXT," + COL_PATIENT_WEIGHT + " TEXT," +
            COL_PATIENT_APPOINTMENT_DATE + " TEXT)";


    public DBHelper(@Nullable Context context) {
        super(context, "Patient.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertDataToDatabase(SQLiteDatabase database, Patient patient) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PATIENT_NAME, patient.getPatientName());
        contentValues.put(COL_PATIENT_AGE, patient.getPatientAge());
        contentValues.put(COL_PATIENT_BLOODGROUP, patient.getPatientBloodGroup());
        contentValues.put(COL_PATIENT_GENDER, patient.getPatientGender());
        contentValues.put(COL_PATIENT_HEIGHT, patient.getPatientHeight());
        contentValues.put(COL_PATIENT_WEIGHT, patient.getPatientWeight());
        contentValues.put(COL_PATIENT_APPOINTMENT_DATE, patient.getAppointmentDate());

        database.insert(TABLE_NAME, null, contentValues);
    }

    public void updateDataToDatabase(SQLiteDatabase database, Patient patient) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_PATIENT_NAME, patient.getPatientName());
        contentValues.put(COL_PATIENT_AGE, patient.getPatientAge());
        contentValues.put(COL_PATIENT_BLOODGROUP, patient.getPatientBloodGroup());
        contentValues.put(COL_PATIENT_GENDER, patient.getPatientGender());
        contentValues.put(COL_PATIENT_HEIGHT, patient.getPatientHeight());
        contentValues.put(COL_PATIENT_WEIGHT, patient.getPatientWeight());
        contentValues.put(COL_PATIENT_APPOINTMENT_DATE, patient.getAppointmentDate());

        database.update(TABLE_NAME, contentValues, "WHERE " + COL_ID + "=" + patient.getPatientID(), null);
    }

    public void deleteDataFromDatabase(SQLiteDatabase database, Patient patient) {
        database.delete(TABLE_NAME, "WHERE " + COL_ID + "=" + patient.getPatientID(), null);
    }

    public ArrayList<Patient> getDataFromDatabase(SQLiteDatabase database) {
        ArrayList<Patient> patientsLists = new ArrayList<>();

        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Patient patientInfo = new Patient();

                patientInfo.setPatientName(cursor.getString(cursor.getColumnIndex(COL_PATIENT_NAME)));
                patientInfo.setPatientID(cursor.getInt(cursor.getColumnIndex(COL_ID)));
                patientInfo.setAppointmentDate(cursor.getString(cursor.getColumnIndex(COL_PATIENT_APPOINTMENT_DATE)));
                patientInfo.setPatientAge(cursor.getString(cursor.getColumnIndex(COL_PATIENT_AGE)));
                patientInfo.setPatientGender(cursor.getString(cursor.getColumnIndex(COL_PATIENT_GENDER)));
                patientInfo.setPatientHeight(cursor.getString(cursor.getColumnIndex(COL_PATIENT_HEIGHT)));
                patientInfo.setPatientWeight(cursor.getString(cursor.getColumnIndex(COL_PATIENT_WEIGHT)));
                patientInfo.setPatientBloodGroup(cursor.getString(cursor.getColumnIndex(COL_PATIENT_BLOODGROUP)));

                patientsLists.add(patientInfo);

            } while (cursor.moveToNext());

            cursor.close();
        }
        return patientsLists;
    }

}
