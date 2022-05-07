package com.example.plink;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        listClass.add(new Class(1000, "Mật mã học cơ sở", ""));
//        listClass.add(new Class(1001, "An toàn mạng", ""));
//        listClass.add(new Class(1002, "Kỹ thuật giấu tin", ""));
//        listClass.add(new Class(1003, "Kiểm thử xâm nhập", ""));
//        listClass.add(new Class(1004, "Mạng máy tính", ""));

        listView = findViewById(R.id.lv_class);

        sqliHelper = new ClassCRUD(HomeActivity.this);

//        sqliHelper.QueryData("drop table if exists class");

//        sqliHelper.QueryData("create table if not exists class(id integer primary key, name text, note text)");
//        Class lop = new Class(1002, "Kỹ thuật giấu tin", "test");
//        sqliHelper.insertClass(lop);

        listClass = sqliHelper.getAllClasses();
//        System.out.println("----------------"+ listClass.size());

        adapter = new ClassAdapter(listClass, HomeActivity.this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
