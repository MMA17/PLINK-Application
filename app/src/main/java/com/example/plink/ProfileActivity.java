package com.example.plink;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import components.member.Member;

public class ProfileActivity extends AppCompatActivity {
    private Button btnEdit;
    private Member member;
    private TextView username,email,DOB,phone,role;

    private static final int EDIT_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnEdit = findViewById(R.id.btnEdit);
        Intent intent = getIntent();
        member = (Member) intent.getSerializableExtra("Member");
        initView(member);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(ProfileActivity.this,EditUserActivity.class);
                t.putExtra("Member",member);
                startActivityForResult(t,EDIT_CODE);
            }
        });
    }

    private void initView(Member member) {
        username = findViewById(R.id.txtUsername);
        email = findViewById(R.id.email);
        DOB = findViewById(R.id.DOB);
        phone = findViewById(R.id.phone);
        role = findViewById(R.id.txtRole);

        username.setText(member.getName());
        email.setText(member.getEmail());
        DOB.setText(member.getDOB());
        phone.setText(member.getPhone());
        role.setText(member.getRole());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if(requestCode == EDIT_CODE && resultCode == RESULT_OK){
           member = (Member) data.getSerializableExtra("Member");
           username.setText(member.getName());
           email.setText(member.getEmail());
           DOB.setText(member.getDOB());
       }
    }
}