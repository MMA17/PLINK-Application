package com.example.plink;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import components.classes.Class;
import components.classmember.ClassMemberCRUD;
import components.homework.Homework;
import components.homework.HomeworkAdapter;
import components.homework.HomeworkCRUD;
import components.member.Member;

public class ExcerciseActivity extends AppCompatActivity {
    private static int CREATE_HOMEWORK = 7777;
    private Member member,author;
    private List<Homework> homeworkList;
    private Class c;
    private ListView listView;
    private HomeworkAdapter adapter;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise);
        listView = findViewById(R.id.excercise_listview);
        Intent intent = getIntent();
        member = (Member) intent.getSerializableExtra("member");
        c = (Class) intent.getSerializableExtra("class");
        ClassMemberCRUD classMemberCRUD = new ClassMemberCRUD(this);
        author = classMemberCRUD.getOwnerfromClass(c.getId());

        Toolbar toolbar = findViewById(R.id.excercise_toolbar);
        setSupportActionBar(toolbar);

        fab = findViewById(R.id.fab_excercise);
        init();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(member.getId() == author.getId()){
                    Intent intent1 = new Intent(ExcerciseActivity.this,CreateHomeworkActivity.class);
                    intent1.putExtra("class",c);
                    startActivity(intent1);
                }
                else{
                    Toast.makeText(ExcerciseActivity.this,"You cant create Homework",Toast.LENGTH_SHORT).show();
                }

            }
        });




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

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init(){
        homeworkList = new HomeworkCRUD(this).getHomeworkByClass(c);
        adapter = new HomeworkAdapter(homeworkList,ExcerciseActivity.this,author,member);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if(homeworkList.size()<=0){
            TextView textView = findViewById(R.id.textViewExcercise);
            textView.setText("Chưa có bài tập nào");
        }
        if(member.getId() != author.getId()){

            fab.setVisibility(View.INVISIBLE);
        }
    }
}