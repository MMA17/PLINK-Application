package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import components.comment.Comment;
import components.comment.CommentAdapter;
import components.comment.CommentCRUD;
import components.file.File;
import components.file.FileAdapter;
import components.file.FileCRUD;
import components.member.Member;
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
        avatar.setImageResource(R.drawable.avatar2);

        //Set author's name
        TextView author_name = (TextView) findViewById(R.id.PostUsername);

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

//        Get File in this post
        ListView lv_file = findViewById(R.id.lv_file);
        FileCRUD sql = new FileCRUD(PostDetailActivity.this);
        List<File> listFile = sql.getAllFileOfPost(post);
        FileAdapter adapterFile = new FileAdapter(listFile, PostDetailActivity.this);
        lv_file.setAdapter(adapterFile);
        adapterFile.notifyDataSetChanged();


//        Get comment of this post
        ListView lv_comment = findViewById(R.id.lv_comment);
        CommentCRUD sqliteHelper = new CommentCRUD(PostDetailActivity.this);
        List<Comment> listComment = sqliteHelper.getCommentbyPostId(post.getId());
        CommentAdapter adapter = new CommentAdapter(listComment,PostDetailActivity.this, post);

        lv_comment.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        Button add_cmt = (Button) findViewById(R.id.btn_comment_post);
        add_cmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = ((EditText) findViewById(R.id.Comment_content)).getText().toString();

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                CommentCRUD crud = new CommentCRUD(PostDetailActivity.this);
                Comment c = new Comment();
                c.setCreated_at(dtf.format(now));
                c.setContent(content);
                c.setAuthorid(member.getId());
                c.setPostid(post.getId());

                if (crud.addComment(c) == true) {
                    Toast.makeText(PostDetailActivity.this, "Thêm bình luận thành công!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
                else {
                    Toast.makeText(PostDetailActivity.this, "Thêm bình luận thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}