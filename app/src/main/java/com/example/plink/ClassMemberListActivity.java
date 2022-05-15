package com.example.plink;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import components.classmember.ClassMember;
import components.classmember.ClassMember2Adapter;
import components.classmember.ClassMemberCRUD;
import components.classes.Class;
import components.member.Member;


public class ClassMemberListActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    ClassMember2Adapter adapter;
    ListView lv;
    private Class lop;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_members_list);
        Intent intent = getIntent();
        lop = (Class) intent.getSerializableExtra("lop");
        toolbar = findViewById(R.id.classmemberlist_toolbar);
        setSupportActionBar(toolbar);

        ClassMemberCRUD crud = new ClassMemberCRUD(ClassMemberListActivity.this);
        List<Member> memberList = crud.getMemberfromClass(lop);
        adapter = new ClassMember2Adapter(memberList, ClassMemberListActivity.this, lop);

        lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(adapter);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClassMemberListActivity.this, AddClassMemberActivity.class);
                intent.putExtra("class", lop);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
//        Class lop = new Class(2,"","");
        ClassMemberCRUD crud = new ClassMemberCRUD(ClassMemberListActivity.this);
        List<Member> memberList = crud.getMemberfromClass(lop);
        adapter = new ClassMember2Adapter(memberList, ClassMemberListActivity.this, lop);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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