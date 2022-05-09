package com.example.plink;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_members_list);
        Class lop = new Class(2,"","");
        ListView lv = (ListView) findViewById(R.id.listview);

        ClassMemberCRUD crud = new ClassMemberCRUD(ClassMemberListActivity.this);
        List<Member> memberList = crud.getMemberfromClass(lop);

        lv.setAdapter(new ClassMember2Adapter(memberList, ClassMemberListActivity.this, lop));
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

}