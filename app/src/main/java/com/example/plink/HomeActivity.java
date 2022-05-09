package com.example.plink;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import components.classes.Class;
import components.classes.ClassAdapter;
import components.classes.ClassCRUD;
import components.classmember.ClassMember;
import components.classmember.ClassMemberAdapter;
import components.classmember.ClassMemberCRUD;
import components.member.Member;

public class HomeActivity extends AppCompatActivity {

    private ListView listView;
    private List<Class> listClass = new ArrayList<>();
    private ClassMemberAdapter adapter;
    private ClassMemberCRUD sqliHelper;
    private FloatingActionButton fab;
    private Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent i = getIntent();
        member = (Member) i.getSerializableExtra("Member");
        fab = findViewById(R.id.fab);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        listView = findViewById(R.id.lv_class);

        sqliHelper = new ClassMemberCRUD(HomeActivity.this);
        listClass = sqliHelper.getClassbyMember(member,HomeActivity.this);

        adapter = new ClassMemberAdapter(listClass, HomeActivity.this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.bell){
            Toast.makeText(HomeActivity.this,"Nelll",Toast.LENGTH_SHORT).show();
        }
        else{
            Intent i = new Intent(this,ProfileActivity.class);
            i.putExtra("Member",member);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}

