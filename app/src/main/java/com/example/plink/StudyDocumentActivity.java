package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import components.classes.Class;
import components.file.File;
import components.file.FileAdapter;
import components.file.FileCRUD;
import components.post.PostCRUD;

public class StudyDocumentActivity extends AppCompatActivity {
    private ListView lv;
    private FileAdapter adapter;
    private List<File> file;
    private FileCRUD filecrud;
    private Context context;
    private PostCRUD postcrud;
    private Class c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_document);
        Toolbar toolbar = findViewById(R.id.document_toolbar);
        setSupportActionBar(toolbar);
        filecrud = new FileCRUD(StudyDocumentActivity.this);
        postcrud = new PostCRUD(StudyDocumentActivity.this);

        lv=findViewById(R.id.lv_file1);

        Intent intent = getIntent();
        c = (Class) intent.getSerializableExtra("class");
        file = filecrud.getFileFromListPost(postcrud.getPostByClass(c));
        adapter = new FileAdapter(file,StudyDocumentActivity.this);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();



//        Button btnDSlop = (Button) findViewById(R.id.buttons1);
//        btnDSlop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myLink = new Intent(Intent.ACTION_VIEW);
//                myLink.setData(Uri.parse("https://google.com"));
//                startActivity(myLink);
//            }
//        });
//        Button btnBDlop = (Button) findViewById(R.id.buttons2);
//        btnBDlop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myLink1 = new Intent(Intent.ACTION_VIEW);
//                myLink1.setData(Uri.parse("https://google.com"));
//                startActivity(myLink1);
//            }
//        });
//        Button btnBT = (Button) findViewById(R.id.buttons3);
//        btnBT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myLink2 = new Intent(Intent.ACTION_VIEW);
//                myLink2.setData(Uri.parse("https://google.com"));
//                startActivity(myLink2);
//            }
//        });

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