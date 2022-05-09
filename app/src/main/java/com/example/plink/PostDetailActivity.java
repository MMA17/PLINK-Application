package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import adapter.CommentAdapter;
import components.classes.Class;
import components.member.Member;
import components.member.MemberCRUD;
import components.post.Post;

public class PostDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView ;
    private CommentAdapter commentAdapter;
    private Context context;

    private Member member;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        Intent i = getIntent();
        member = (Member) i.getSerializableExtra("user");
        post = (Post) i.getSerializableExtra("post");
        String authorName = i.getStringExtra("authorName");
        ImageView avatar = findViewById(R.id.PostAvatar);
        // Set avatar
        avatar.setImageResource(R.drawable.avatar);

        //Set author's name
        TextView author_name = (TextView) findViewById(R.id.PostUsername);
//        Member author = new Member();
//        author.setId(post.getAuthor());
//        System.out.println(post.getAuthor());
//        author = (new MemberCRUD(context)).getMemberByID(author);
//        System.out.println(author.getName());
//        author_name.setText(author.getName());

        System.out.println(authorName);
        author_name.setText(authorName);

        TextView position = (TextView) findViewById(R.id.position);
        position.setText("Giáo viên");

        TextView title = (TextView) findViewById(R.id.Post_title);
        title.setText(post.getTitle());
        TextView PostDate = (TextView) findViewById(R.id.postDate);
        PostDate.setText(post.getCreate_at());
        TextView PostDes = (TextView) findViewById(R.id.PostDescription);
        PostDes.setText(post.getContent());

        initComment();
    }

    private void initComment() {

    }
}