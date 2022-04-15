package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import components.member.*;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity  {
    private EditText txtPasswd,txtPhone,txtUsername,txtMail;
    private Button btnRegister;
    private TextView btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        Button btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtUsername.getText().toString();
                String phone = txtPhone.getText().toString();
                String email = txtMail.getText().toString();
                String password = txtPasswd.getText().toString();
                Member user = new Member();
                user.setName(name);
                user.setPhone(phone);
                user.setEmail(email);
                user.setPassword(password);
                MemberCRUD memberCURD = new MemberCRUD(RegisterActivity.this);
//                memberCURD.onCreate();
                if(memberCURD.getMemberbyPhone(user.getPhone()) != null){
                    //Tai khoan chua co trong DB
                    System.out.println("Tai khoan chua co trong DB");
                    memberCURD.insertMember(user);
                    Toast.makeText(RegisterActivity.this,"Đăng kí thành công",Toast.LENGTH_LONG).show();
                    Intent i = new Intent();
                    i.putExtra("data",user);
                    setResult(RESULT_OK,i);
                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Tài khoản tồn tại",Toast.LENGTH_LONG).show();
                    user = null;
                    //txtUsername.setText("");
                    txtPhone.setText("");
                    txtPasswd.setText("");
                    txtMail.setText("");

                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED,null);
                finish();
            }
        });
    }
    private void init(){
        txtPhone = findViewById(R.id.txtPhone);
        txtPasswd = findViewById(R.id.txtPass);
        txtUsername = findViewById(R.id.txtUsername);
        txtMail = findViewById(R.id.txtEmail);
        btnCancel = findViewById(R.id.Cancle);
    }
}