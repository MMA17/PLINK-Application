package com.example.plink;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

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

public class HomeActivity extends AppCompatActivity {

    private ListView listView;
    private List<Class> listClass = new ArrayList<>();
    private ClassAdapter adapter;
    private ClassCRUD sqliHelper;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fab = findViewById(R.id.fab);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
//        listClass.add(new Class(1000, "Mật mã học cơ sở", ""));
//        listClass.add(new Class(1001, "An toàn mạng", ""));
//        listClass.add(new Class(1002, "Kỹ thuật giấu tin", ""));
//        listClass.add(new Class(1003, "Kiểm thử xâm nhập", ""));
//        listClass.add(new Class(1004, "Mạng máy tính", ""));

        listView = findViewById(R.id.lv_class);

        sqliHelper = new ClassCRUD(HomeActivity.this);

//        sqliHelper.QueryData("drop table if exists class");

        sqliHelper.QueryData("create table if not exists class(id integer primary key, name text, note text)");
//        Class lop = new Class(1001, "An toàn mạng", "test");
//        sqliHelper.insertClass(lop);

//        Class lop = new Class(1002, "Lập trình Android", "MAD is fun");
//        sqliHelper.insertClass(lop);
        listClass = sqliHelper.getAllClasses();
//        System.out.println("----------------"+ listClass.size());

        adapter = new ClassAdapter(listClass, HomeActivity.this);
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
        return super.onOptionsItemSelected(item);
    }
}

