package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.widget.ListView;

public class CommentActivity extends AppCompatActivity {
    ListView listcmt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        listcmt = findViewById(R.id.listcomment);


    }
}