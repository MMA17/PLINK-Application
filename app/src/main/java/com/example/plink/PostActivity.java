package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        ImageView addPost = (ImageView) findViewById(R.id.btn_add_post);
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PostActivity.this, CreatePostActivity.class);
                startActivity(i);
            }
        });

        ConstraintLayout LayoutPost = (ConstraintLayout) findViewById(R.id.constraintLayoutPost);
        LayoutPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PostActivity.this, PostDetailActivity.class);
                startActivity(i);
            }
        });
    }
}