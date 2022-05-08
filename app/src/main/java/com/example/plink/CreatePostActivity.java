package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

import components.classes.Class;
import components.member.Member;
import components.post.Post;
import components.post.PostCRUD;

public class CreatePostActivity extends AppCompatActivity {
    private Member member;
    private Class c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        Intent i = getIntent();
        member = (Member) i.getSerializableExtra("user");
        c = (Class) i.getSerializableExtra("class");

        Button createPost = (Button) findViewById(R.id.btn_create_post);
        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String post_title = ((EditText) findViewById(R.id.add_post_title)).getText().toString();
                String post_content = ((EditText) findViewById(R.id.add_post_content)).getText().toString();
                Date post_date = Calendar.getInstance().getTime();
                System.out.println(post_date);
                int classID = c.getId();
                int author = member.getId();

                Post p = new Post();
                p.setTitle(post_title);
                p.setContent(post_content);
                p.setCreate_at(post_date.toString());
                p.setAuthor(author);
                p.setClassid(classID);

                PostCRUD crud = new PostCRUD(CreatePostActivity.this);
                if (crud.insertPost(p)) {
                    Toast.makeText(CreatePostActivity.this, "Tạo bài đăng thành công!", Toast.LENGTH_LONG).show();
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
                else {
                    Toast.makeText(CreatePostActivity.this, "Tạo bài thất bại!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}