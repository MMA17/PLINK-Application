package com.example.plink;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import components.member.Member;
import components.member.MemberCRUD;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout linearLayout;
    private ImageView btnShowPasswd;
    private EditText txtPasswd,txtPhone;
    private TextView txtRegister;
    private final static int REQUEST_CODE = 10000;
    private Button btnLogin;
    private Member member = new Member();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        txtRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }
    private void init(){
        txtRegister = findViewById(R.id.Register);
        txtPhone = findViewById(R.id.txtPhone);
        txtPasswd = findViewById(R.id.passwd);
        btnLogin = findViewById(R.id.btnLogin);
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

        }
    }
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.Register){
            Intent i = new Intent(this, RegisterActivity.class);
            startActivityForResult(i,REQUEST_CODE);
        }
        if(view.getId() == R.id.btnLogin){
            MemberCRUD memberCRUD = new MemberCRUD(MainActivity.this);
            if(txtPhone.getText() != null && txtPasswd.getText() != null){
                member.setPassword(txtPasswd.getText().toString());
                member.setPhone(txtPhone.getText().toString());
                System.out.println("Check ne");
                if(memberCRUD.checkLogin(member)){
                    //DAng nhap thanh cong
                    System.out.println("Login oke");
                    member = memberCRUD.getMemberbyPhone(member.getPhone());
                    Toast.makeText(MainActivity.this,"Login successful",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(this, HomeActivity.class);
                    i.putExtra("Member",member);
                    startActivity(i);
                }
                else{
                    Toast.makeText(MainActivity.this,"Login failed",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}