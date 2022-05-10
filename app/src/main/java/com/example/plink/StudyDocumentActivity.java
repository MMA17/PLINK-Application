package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudyDocumentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_document);

        Button btnDSlop = (Button) findViewById(R.id.buttons1);
        btnDSlop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myLink = new Intent(Intent.ACTION_VIEW);
                myLink.setData(Uri.parse("https://google.com"));
                startActivity(myLink);
            }
        });
        Button btnBDlop = (Button) findViewById(R.id.buttons2);
        btnBDlop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myLink1 = new Intent(Intent.ACTION_VIEW);
                myLink1.setData(Uri.parse("https://google.com"));
                startActivity(myLink1);
            }
        });
        Button btnBT = (Button) findViewById(R.id.buttons3);
        btnBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myLink2 = new Intent(Intent.ACTION_VIEW);
                myLink2.setData(Uri.parse("https://google.com"));
                startActivity(myLink2);
            }
        });
    }
}