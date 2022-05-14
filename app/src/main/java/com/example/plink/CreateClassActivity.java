package com.example.plink;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import components.classes.Class;
import components.classes.ClassCRUD;
import components.classmember.ClassMember;
import components.classmember.ClassMemberCRUD;
import components.member.Member;
import components.member.MemberAdapter;
import components.member.MemberCRUD;

public class CreateClassActivity extends AppCompatActivity {
    private EditText edtName, edtDes;
    private Button btnSubmit;
    private Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        Intent intent = getIntent();
        member = (Member) intent.getSerializableExtra("user");

        edtName = findViewById(R.id.edt_nameClass);
        edtDes = findViewById(R.id.edtDes);
        btnSubmit = findViewById(R.id.btnCreateClass);

        ClassCRUD crud = new ClassCRUD(CreateClassActivity.this);
        ClassMemberCRUD classMemberCRUD = new ClassMemberCRUD(CreateClassActivity.this);
        MemberCRUD memberCRUD = new MemberCRUD(CreateClassActivity.this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String des = edtDes.getText().toString();
                if(TextUtils.isEmpty(name)){
                    Toast.makeText(CreateClassActivity.this,"Bạn chưa nhập tên lớp học",Toast.LENGTH_SHORT).show();
                }else {
                    Class c = new Class();
                    c.setName(name);
                    c.setNote(des);

                    Class cc = crud.insertClass(c);
                    ClassMember classMember = new ClassMember(member, cc, 1);
                    classMemberCRUD.insertClassMember(classMember);

                    Toast.makeText(CreateClassActivity.this,"Tạo lớp học thành công!",Toast.LENGTH_SHORT).show();

                    Intent intent1 = new Intent(CreateClassActivity.this, CreateClass2Activity.class);
                    intent1.putExtra("class", cc);
                    intent1.putExtra("user", member);
                    startActivity(intent1);

                    finish();
                }
            }
        });
    }
}
