package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import components.member.Member;

public class EditUserActivity extends AppCompatActivity {
    private TextView txtPositon;
    private EditText username,phone,email;
    private Button btnUpdate,btnDOB;
    private DatePickerDialog datePicker;
    private Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Intent i = getIntent();
        member = (Member) i.getSerializableExtra("Member");
        initView(member);
        initDatePicker();
        btnUpdate = findViewById(R.id.btnEdit);



    }

    private void initView(Member member) {
        username = findViewById(R.id.txtUsername);
        phone = findViewById(R.id.txtPhone);
        email = findViewById(R.id.txtEmail);
        txtPositon=findViewById(R.id.txtPosition);
        btnDOB = findViewById(R.id.btnDOB);

        txtPositon.setText(member.getRole());
        username.setText(member.getName());
        phone.setText(member.getPhone());
        email.setText(member.getEmail());
        btnDOB.setText(member.getDOB());
    }

    private String getDOB() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month+=1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                String date = makeDateString(day,month,year);
                btnDOB.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        datePicker =  new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,dateSetListener,year,month,day);
    }

    private String makeDateString(int day, int month, int year) {
        return day+"/"+month+"/"+year;
    }

    public void openDatePicker(View view){
        datePicker.show();
    }

}