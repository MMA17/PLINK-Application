package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText txtPasswd,txtPhone,txtUsername,txtMail;
    private Button btnRegister;
    private TextView btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtPasswd = findViewById(R.id.passwd);
        txtPhone = findViewById(R.id.txtPhone);
        txtUsername = findViewById(R.id.txtUsername);
        txtMail = findViewById(R.id.txtEmail);
        btnRegister = findViewById(R.id.btnRegister);
        btnCancel = findViewById(R.id.Cancle);
        btnRegister.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnRegister){
            Member mem = new Member();
            mem.setName(txtUsername.getText().toString());
            mem.setPasswd(txtPasswd.getText().toString());
            mem.setPhone(txtPhone.getText().toString());
            mem.setEmail(txtMail.getText().toString());
            Intent intent = new Intent();
            intent.putExtra("data",mem);
            setResult(RESULT_OK,intent);
            finish();
        }
        if(view.getId() == R.id.Cancle){
            //Intent intent = new Intent();
            setResult(RESULT_CANCELED,null);
            finish();
        }
    }
}