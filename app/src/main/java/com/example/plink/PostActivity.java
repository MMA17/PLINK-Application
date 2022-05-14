package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        Toolbar toolbar = findViewById(R.id.post_toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        member = (Member) i.getSerializableExtra("user");
        c = (Class) i.getSerializableExtra("class");

        listView = findViewById(R.id.lv_post);
        sqliHelper = new PostCRUD(PostActivity.this);
        listPost = sqliHelper.getPostByClass(c);

        adapter = new PostAdapter(listPost, PostActivity.this, member, c);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        if(listPost.size() <= 0){
            TextView textView = findViewById(R.id.textViewPost);
            textView.setText("Chưa có bài đăng");
        }
        ImageView addPost = (ImageView) findViewById(R.id.btn_add_post);
        addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PostActivity.this, CreatePostActivity.class);
                i.putExtra("user", member);
                i.putExtra("class", c);
                startActivityForResult(i, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                finish();
                startActivity(getIntent());
            }
        }
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