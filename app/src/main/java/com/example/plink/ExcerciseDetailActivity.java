package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import components.homework.Homework;
import components.homework.HomeworkCRUD;
import components.member.Member;

public class ExcerciseDetailActivity extends AppCompatActivity {
    private Member member,author;
    private Homework excercise;
    private TextView authorname,createdat,content;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_detail);
        Intent intent = getIntent();
        member = (Member) intent.getSerializableExtra("member");
        excercise = (Homework) intent.getSerializableExtra("excercise");
        authorname = findViewById(R.id.excercisedetail_author_name);
        createdat = findViewById(R.id.excercisedetail_createdat);
        content = findViewById(R.id.excercisedetail_content);
        toolbar = findViewById(R.id.excercisedetail_toolbar);
        setSupportActionBar(toolbar);
        initData();
    }

    private void initData() {
        author = new HomeworkCRUD(this).getAuthorHomework(excercise);
        authorname.setText(author.getName());
        createdat.setText(excercise.getCreate_at());
        content.setText(excercise.getContent());
        toolbar.setTitle(excercise.getTitle());
    }
}