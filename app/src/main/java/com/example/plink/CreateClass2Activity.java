package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import components.classes.Class;
import components.classmember.ClassMember;
import components.classmember.ClassMemberCRUD;
import components.member.Member;
import components.member.MemberAdapter;
import components.member.MemberCRUD;

public class CreateClass2Activity extends AppCompatActivity {
    private Button btnFinish;
    private SearchView searchView;
    private ListView listView;
    private List<Member> allMember;
    private MemberAdapter adapter;
    private MemberCRUD memberCRUD;
    private ClassMemberCRUD classMemberCRUD;
    private Class lop;
    private Member user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class2);

        Intent intent = getIntent();
        lop = (Class) intent.getSerializableExtra("class");
        user = (Member) intent.getSerializableExtra("user");

        allMember = new ArrayList<>();
        searchView = findViewById(R.id.tuan_search_view);
        listView = findViewById(R.id.tuan_list_member);
        btnFinish = findViewById(R.id.btnFinish);

        classMemberCRUD = new ClassMemberCRUD(CreateClass2Activity.this);
        memberCRUD = new MemberCRUD(CreateClass2Activity.this);
        allMember = memberCRUD.getAllMembers();

        for (Member m:allMember){
            if (m.getId() == user.getId()){
                allMember.remove(m);
                break;
            }
        }

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapter = new MemberAdapter(allMember, CreateClass2Activity.this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                ClassMember tmp = new ClassMember(allMember.get(i), lop, 0);
                if(classMemberCRUD.insertClassMember(tmp)){
                    Toast.makeText(CreateClass2Activity.this,"Đã thêm thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(CreateClass2Activity.this,"Thêm không thành công", Toast.LENGTH_SHORT).show();
                }
                allMember.remove(i);
                adapter.notifyDataSetChanged();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.search(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.search(newText);
                return true;

            }
        });
    }
}