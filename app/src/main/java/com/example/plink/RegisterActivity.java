package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import components.member.*;
import android.widget.TextView;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtPasswd,txtPhone,txtUsername,txtMail;
    private Button btnRegister;
    private TextView btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText) findViewById(R.id.txtUsername)).getText().toString();
                String phone = ((EditText) findViewById(R.id.txtPhone)).getText().toString();
                String email = ((EditText) findViewById(R.id.txtEmail)).getText().toString();
                String password = ((EditText) findViewById(R.id.txtPass)).getText().toString();
                Member user = new Member();
                user.setName(name);
                user.setPhone(phone);
                user.setEmail(email);
                user.setPassword(password);
                MemberCRUD memberCURD = new MemberCRUD(RegisterActivity.this);
//                memberCURD.onCreate();
                memberCURD.insertMember(user);
            }
        });

       
    }
}