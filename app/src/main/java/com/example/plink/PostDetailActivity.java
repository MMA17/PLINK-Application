package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
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
    private ListView lv_comment;
    private Member member;
    private Post post;
    private String authorName,pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        Toolbar toolbar = findViewById(R.id.postdetail_toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        member = (Member) i.getSerializableExtra("user");
        post = (Post) i.getSerializableExtra("post");
        authorName = i.getStringExtra("authorName");
        pos = i.getStringExtra("position");
        toolbar.setTitle(post.getTitle());
        initView();

//        Get File in this post
        getFileinPost();

//        Get comment of this post
        getCommentinPost();


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
                    Toast.makeText(PostDetailActivity.this, "Th??m b??nh lu???n th??nh c??ng!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
                else {
                    Toast.makeText(PostDetailActivity.this, "Th??m b??nh lu???n th???t b???i", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void getCommentinPost() {
        lv_comment = findViewById(R.id.lv_comment);
        CommentCRUD sqliteHelper = new CommentCRUD(PostDetailActivity.this);
        List<Comment> listComment = sqliteHelper.getCommentbyPostId(post.getId());
        CommentAdapter adapter = new CommentAdapter(listComment,PostDetailActivity.this, post);
        System.out.println(listComment.size() + "List comment");
        lv_comment.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        ImageView avatar = findViewById(R.id.PostAvatar);
        avatar.setImageResource(R.drawable.avatar2);
        TextView author_name = (TextView) findViewById(R.id.PostUsername);
        author_name.setText(authorName);
        TextView position = (TextView) findViewById(R.id.position);
        position.setText(pos);
        TextView title = (TextView) findViewById(R.id.Post_title);
        title.setText(post.getTitle());
        TextView PostDate = (TextView) findViewById(R.id.postDate);
        PostDate.setText(post.getCreate_at());
        TextView PostDes = (TextView) findViewById(R.id.PostDescription);
        PostDes.setText(post.getContent());
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
    private void getFileinPost(){
        ListView lv_file = findViewById(R.id.lv_file);
        FileCRUD sql = new FileCRUD(PostDetailActivity.this);
        List<File> listFile = sql.getAllFileOfPost(post);
        FileAdapter adapterFile = new FileAdapter(listFile, PostDetailActivity.this);
        lv_file.setAdapter(adapterFile);
        adapterFile.notifyDataSetChanged();
    }
}