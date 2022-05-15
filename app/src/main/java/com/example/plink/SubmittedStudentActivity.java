package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import components.membersubmitted.MemberSubmitted;
import components.membersubmitted.MemberSubmittedAdapter;
import components.membersubmitted.MemberSubmittedCRUD;

public class SubmittedStudentActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private int excerciseid;
    private List<MemberSubmitted> memberSubmittedList;
    private MemberSubmittedAdapter adapter;
    private ListView listView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitted_student);
        toolbar = findViewById(R.id.membersubmit_toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        excerciseid = i.getIntExtra("excerciseid",-1);
        initData();
    }

    private void initData() {
        MemberSubmittedCRUD memberSubmittedCRUD = new MemberSubmittedCRUD(this);
        memberSubmittedList = memberSubmittedCRUD.getAllMemberSubmit(excerciseid);
        adapter = new MemberSubmittedAdapter(this,memberSubmittedList);
        listView = findViewById(R.id.membersumit_lv);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if(memberSubmittedList.size() <=0){
            textView = findViewById(R.id.textViewSubmitList);
            textView.setText("Chưa có bài tập");
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