package com.example.plink;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import components.classes.Class;
import components.classes.ClassCRUD;
import components.classmember.ClassMember;
import components.classmember.ClassMemberCRUD;
import components.member.Member;
import components.member.MemberCRUD;

public class AddClassMemberActivity extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    Class lop = new Class();
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class_member);

        searchView = findViewById(R.id.search_view);
        listView = findViewById(R.id.listview);
        MemberCRUD crud2 = new MemberCRUD(AddClassMemberActivity.this);
        ClassMemberCRUD crud = new ClassMemberCRUD(AddClassMemberActivity.this);
        List<Member> memberList = crud2.getAllMembers();
        List<String> nameList = new ArrayList<>();
        for (int i = 0; i <= memberList.size(); i++){
            nameList.add(memberList.get(i).getName());
        }
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, nameList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(AddClassMemberActivity.this, "You Click "+adapterView.getItemAtPosition(i).toString(),Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddClassMemberActivity.this);
                alertDialogBuilder.setMessage("Bán có muốn thêm người dùng này vào lớp không");
                alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ClassMember deleteClassMem = new ClassMember(memberList.get(i), lop, 0);
                        crud.insertClassMember(deleteClassMem);
                        memberList.remove(i);
                        nameList.remove(i);
                        arrayAdapter.notifyDataSetChanged();
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
                AddClassMemberActivity.this.arrayAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                AddClassMemberActivity.this.arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}