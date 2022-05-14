package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import components.classes.Class;
import java.util.Date;

import components.file.File;
import components.file.FileCRUD;
import components.homework.Homework;
import components.homework.HomeworkCRUD;
import components.member.Member;

public class CreateHomeworkActivity extends AppCompatActivity {
    private Class c;
    private DatePickerDialog datePicker;
    private Button btnDeadline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_homework);

        Intent i = getIntent();
        c = (Class) i.getSerializableExtra("class");
        btnDeadline = findViewById(R.id.btnDeadline);
        Button createHomework = (Button) findViewById(R.id.btn_create_homework);
        initDatePicker();
        createHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String homework_title = ((EditText) findViewById(R.id.txtTitle)).getText().toString();
                String homework_content = ((EditText) findViewById(R.id.txtContent)).getText().toString();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                // Homework info
                Homework h = new Homework();
                h.setTitle(homework_title);
                h.setContent(homework_content);
                h.setDeadline(btnDeadline.getText().toString());
                h.setCreate_at(dtf.format(now));
                h.setClassid(c.getId());
                System.out.println("Tạo Homework: "+ h.getCreate_at()+" ~~~"+h.getDeadline());
                //File info
                File f = new File();
                String input = ((EditText) findViewById(R.id.fileURL1)).getText().toString();
                f.setPath(input);
                try {
                    URL fileName = new URL(input);
                    f.setName(fileName.getPath());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                f.setSize(0);


                HomeworkCRUD crud = new HomeworkCRUD(CreateHomeworkActivity.this);
                FileCRUD fileCrud = new FileCRUD(CreateHomeworkActivity.this);

                h = crud.insertHomework(h);
                if (h != null ) {
                    if(input != ""){
                        fileCrud.insertFileToExercise(f,h);
                    }
                    Toast.makeText(CreateHomeworkActivity.this,"Tạo bài tập thành công",Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    Toast.makeText(CreateHomeworkActivity.this, "Tạo thất bại",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                String date = makeDateString(day,month,year);

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