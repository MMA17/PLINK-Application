package com.example.plink;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import components.classes.Class;
import components.classes.ClassCRUD;
import components.classmember.ClassMember;
import components.classmember.ClassMemberCRUD;
import components.member.Member;
import components.member.MemberAdapter;
import components.member.MemberCRUD;

public class AddClassMemberActivity extends AppCompatActivity {

    private SearchView searchView;
    private ListView listView;
    private Class lop;
    private MemberAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class_member);
        Intent intent = getIntent();
        lop = (Class) intent.getSerializableExtra("class");
        searchView = findViewById(R.id.search_view);
        listView = findViewById(R.id.list_member);

        MemberCRUD crud2 = new MemberCRUD(AddClassMemberActivity.this);
        ClassMemberCRUD crud = new ClassMemberCRUD(AddClassMemberActivity.this);

        Button btnDone = (Button) findViewById(R.id.btn_done);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        List<Member> memberList = crud2.getMemberNotInClass(lop,AddClassMemberActivity.this);
        arrayAdapter = new MemberAdapter(memberList,AddClassMemberActivity.this,lop);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddClassMemberActivity.this);
                alertDialogBuilder.setMessage("Bán có muốn thêm người dùng này vào lớp không");
                alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ClassMember insertClassMem = new ClassMember(memberList.get(pos), lop, 0);
                        boolean inserted = crud.insertClassMember(insertClassMem);
                        if (inserted == true){
                            Toast.makeText(AddClassMemberActivity.this,"Đã thêm thành công", Toast.LENGTH_SHORT).show();
                            memberList.remove(pos);
                            arrayAdapter.notifyDataSetChanged();
                        }else {
                            memberList.remove(pos);
                            arrayAdapter.notifyDataSetChanged();
                        }
                    }
                });
                alertDialogBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                alertDialogBuilder.show();

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                arrayAdapter.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrayAdapter.search(newText);
                return true;

            }
        });
    }
}