package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_homework);

        Intent i = getIntent();
        c = (Class) i.getSerializableExtra("class");

        Button createHomework = (Button) findViewById(R.id.btn_create_homework);
        createHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String homework_title = ((EditText) findViewById(R.id.txtTitle)).getText().toString();
                String homework_content = ((EditText) findViewById(R.id.txtContent)).getText().toString();
                Date deadline = Calendar.getInstance().getTime();
                System.out.println(deadline);

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                // Homework info
                Homework h = new Homework();
                h.setTitle(homework_title);
                h.setContent(homework_content);
                h.setDeadline(deadline.toString());
                h.setCreate_at(dtf.format(now));
                h.setClassid(c.getId());

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
                if (h != null && fileCrud.insertFileToExercise(f,h)) {
                    Toast.makeText(CreateHomeworkActivity.this,"Tạo bài tập thành công",Toast.LENGTH_LONG).show();
//                    Intent returnIntent = new Intent();
//                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
                else{
                    Toast.makeText(CreateHomeworkActivity.this, "Tạo thất bại",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}