package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import adapter.CommentAdapter;

public class PostDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView ;
    private CommentAdapter commentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        //recyclerView.findViewById(R.id.rvComment);

        //init recycleview
        initComment();
    }

    private void initComment() {

    }
}