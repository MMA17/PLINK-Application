package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import components.file.File;
import components.file.FileCRUD;
import components.homework.Homework;
import components.homework.HomeworkCRUD;
import components.member.Member;
import components.membersubmitted.MemberSubmitted;
import components.membersubmitted.MemberSubmittedCRUD;

public class ExcerciseDetailActivity extends AppCompatActivity {
    private Member member,author;
    private Homework excercise;
    private MemberSubmitted memberSubmitted;
    private MemberSubmittedCRUD memberSubmittedCRUD;
    private TextView authorname,createdat,content,deadline,checksubmit;
    private Toolbar toolbar;
    private Button btnSubmit,btnListSubmit;
    private EditText url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_detail);
        Intent intent = getIntent();
        member = (Member) intent.getSerializableExtra("member");
        excercise = (Homework) intent.getSerializableExtra("excercise");
        author = (Member) intent.getSerializableExtra("author");
        initView();
        initData();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int memberid = member.getId();
                int excerciseid= excercise.getId();

                if(url.getText().toString() == ""){
                    Toast.makeText(ExcerciseDetailActivity.this,"Điền URL đi đã!",Toast.LENGTH_SHORT).show();
                }
                else{
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyyy HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();

                    File f = new File();
                    String input =  url.getText().toString();
                    f.setPath(input);
                    try {
                        URL filepath = new URL(input);
                        f.setName(filepath.getPath());
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    f.setSize(0);
                    FileCRUD fileCRUD = new FileCRUD(ExcerciseDetailActivity.this);
                    int fileid = fileCRUD.insertExcerciseFile(f,excercise);
                    memberSubmitted.setFileid(fileid);
                    memberSubmitted.setTimesubmit(dtf.format(now));
                    if(fileid != -1 &&memberSubmittedCRUD.insertSubmit(memberSubmitted)){
                        Toast.makeText(ExcerciseDetailActivity.this,"Nộp bài thành công",Toast.LENGTH_SHORT).show();
                        memberSubmittedCRUD.checkDeadline(memberSubmitted, excercise.getDeadline());
                        finish();
                    }
                    else{
                        Toast.makeText(ExcerciseDetailActivity.this,"Có lỗi xảy ra",Toast.LENGTH_SHORT).show();
                        initData();
                    }
                }
            }
        });

        btnListSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(ExcerciseDetailActivity.this,SubmittedStudentActivity.class);
                intent.putExtra("excerciseid",excercise.getId());
                startActivity(intent);
            }
        });

    }

    private void initView() {
        authorname = findViewById(R.id.excercisedetail_author_name);
        createdat = findViewById(R.id.excercisedetail_createdat);
        content = findViewById(R.id.excercisedetail_content);
        toolbar = findViewById(R.id.excercisedetail_toolbar);
        deadline = findViewById(R.id.excercisedetail_deadline);
        url = findViewById(R.id.urlExcercise);
        btnSubmit = findViewById(R.id.excercise_submitbtn);
        checksubmit = findViewById(R.id.checksubmit_excercisedetail);
        setSupportActionBar(toolbar);
        btnListSubmit = findViewById(R.id.excercis_listsubmit);
        btnListSubmit.setVisibility(View.GONE);
    }

    private void initData() {
        author = new HomeworkCRUD(this).getAuthorHomework(excercise);
        authorname.setText(author.getName());
        createdat.setText(excercise.getCreate_at());
        content.setText(excercise.getContent());
        toolbar.setTitle(excercise.getTitle());
        deadline.setText("Hạn nộp: "+excercise.getDeadline());
        memberSubmitted = new MemberSubmitted();
        memberSubmitted.setMemberid(member.getId());
        memberSubmitted.setExcerciseid(excercise.getId());
        memberSubmittedCRUD = new MemberSubmittedCRUD(this);

        if(!checkAuthor()){
            isSumit();
        }
    }

    private boolean checkAuthor() {
        if(member.getId() == author.getId()){
            btnListSubmit.setVisibility(View.VISIBLE);
            btnSubmit.setVisibility(View.GONE);
            return true;
        }
        return false;
    }


    private void isSumit() {
        if(memberSubmittedCRUD.checkSubmit(memberSubmitted)){
            btnSubmit.setEnabled(false);
            btnSubmit.setText("Đã nộp bài");
            memberSubmitted = memberSubmittedCRUD.getMemberSumit(memberSubmitted);
            if(memberSubmittedCRUD.checkDeadline(memberSubmitted,excercise.getDeadline())){
                checksubmit.setText("Đã nộp");
            }else{
                checksubmit.setText("Nộp muộn");
            }
        }
        else{
            checksubmit.setText("Chưa hoàn thành");
            checksubmit.setTextColor(Color.parseColor("#ff1a1a"));
            btnSubmit.setEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}