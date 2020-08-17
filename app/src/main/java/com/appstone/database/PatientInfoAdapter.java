package com.appstone.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PatientInfoAdapter extends RecyclerView.Adapter<PatientInfoAdapter.PatientInfoHolder> {


    private Context context;
    private ArrayList<Patient> patientDetails;
    private PatientClickListener listener;


    public PatientInfoAdapter(Context context, ArrayList<Patient> patientDetails) {
        this.context = context;
        this.patientDetails = patientDetails;
    }


    public void setListener(PatientClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public PatientInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PatientInfoHolder holder = new PatientInfoHolder(LayoutInflater.from(context).inflate(R.layout.cell_patient, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PatientInfoHolder holder, int position) {
        final Patient patient = patientDetails.get(position);

        holder.mTvPatientName.setText(patient.getPatientName());
        holder.mTvPatientAge.setText(patient.getPatientAge());
        holder.mTvPatientBloodGroup.setText(patient.getPatientBloodGroup());
        holder.mTvAppointmentDate.setText(patient.getAppointmentDate());

        holder.mRlEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onUpdateClicked(patient);
                }
            }
        });

        holder.mRlDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDeleteClicked(patient);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientDetails.size();
    }

    //1. Create a class for the view holder (cell)
    //2. Viewholder extend to RecyclerView.ViewHolder
    //3. FindViewByIds in side that View Holder
    //4. Extend the parent class to RecyclerView.Adapter with the view Holder
    //5. Implement the override methods


    class PatientInfoHolder extends RecyclerView.ViewHolder {


        private TextView mTvPatientName;
        private TextView mTvPatientAge;
        private TextView mTvPatientBloodGroup;
        private TextView mTvAppointmentDate;

        private RelativeLayout mRlEdit;
        private RelativeLayout mRlDelete;

        public PatientInfoHolder(@NonNull View itemView) {
            super(itemView);

            mTvPatientName = itemView.findViewById(R.id.tv_patient_name);
            mTvPatientAge = itemView.findViewById(R.id.tv_patient_age);
            mTvPatientBloodGroup = itemView.findViewById(R.id.tv_patient_bloodgroup);
            mTvAppointmentDate = itemView.findViewById(R.id.tv_patient_appointment_date);

            mRlEdit = itemView.findViewById(R.id.rl_edit);
            mRlDelete = itemView.findViewById(R.id.rl_delete);
        }
    }


    public interface PatientClickListener {
        void onUpdateClicked(Patient patient);

        void onDeleteClicked(Patient patient);
    }

}
