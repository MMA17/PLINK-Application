package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {
    private Button btnEdit;
    private EditText name;
    private static final int EDIT_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnEdit = findViewById(R.id.btnEdit);
        name = findViewById(R.id.txtUsername);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent();

                startActivityForResult(t,EDIT_CODE);
            }
        });
    }

}