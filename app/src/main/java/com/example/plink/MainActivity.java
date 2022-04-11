package com.example.plink;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import components.member.Member;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout linearLayout;
    private ImageView btnShowPasswd;
    private EditText txtPasswd,txtPhone;
    private TextView txtRegister;
    private final static int REQUEST_CODE = 10000;
    private Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtRegister = findViewById(R.id.Register);
        txtRegister.setOnClickListener(this);
        txtPhone = findViewById(R.id.txtPhone);
        txtPasswd = findViewById(R.id.passwd);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if(data!= null){
                member = (Member)data.getSerializableExtra("data");
                txtPhone.setText(member.getPhone().toString());
                txtPasswd.setText(member.getPassword().toString());
                Toast.makeText(this,"Đăng ký thành công",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"Đăng nhập",Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onClick(View view) {
        Toast.makeText(this,"Dangg ki",Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, RegisterActivity.class);
        startActivityForResult(i,REQUEST_CODE);
    }
}