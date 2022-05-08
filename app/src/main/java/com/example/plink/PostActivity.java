package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import components.classes.Class;
import components.member.Member;
import components.post.Post;
import components.post.PostAdapter;
import components.post.PostCRUD;

public class PostActivity extends AppCompatActivity {

    private List<Post> listPost = new ArrayList<>();
    private ListView listView;
    private PostAdapter adapter;
    private PostCRUD sqliHelper;
    private Member member;
    private Class c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent i = getIntent();
        member = (Member) i.getSerializableExtra("user");
        c = (Class) i.getSerializableExtra("class");

        listView = findViewById(R.id.lv_post);
        sqliHelper = new PostCRUD(PostActivity.this);
        listPost = sqliHelper.getPostByClass(c);

        adapter = new PostAdapter(listPost, PostActivity.this);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ImageView addPost = (ImageView) findViewById(R.id.btn_add_post);
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PostActivity.this, CreatePostActivity.class);
                startActivity(i);
            }
        });


    }
}