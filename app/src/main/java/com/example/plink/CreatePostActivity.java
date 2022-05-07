package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

import components.post.Post;
import components.post.PostCRUD;

public class CreatePostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        Button createPost = (Button) findViewById(R.id.btn_create_post);
        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String post_title = ((EditText) findViewById(R.id.add_post_title)).getText().toString();
                String post_content = ((EditText) findViewById(R.id.add_post_content)).getText().toString();
                Date post_date = Calendar.getInstance().getTime();
                System.out.println(post_date);
                int classID = 2;
                int author = 2;

                Post p = new Post();
                p.setTitle(post_title);
                p.setContent(post_content);
                p.setCreate_at(post_date.toString());
                p.setAuthor(author);
                p.setClassid(classID);

                PostCRUD crud = new PostCRUD(CreatePostActivity.this);
                crud.insertPost(p);

                Toast.makeText(CreatePostActivity.this, "Tao bai dang thanh cong!!!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}