package com.example.plink;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.DialogInterface;
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
import components.classes.ClassAdapter2;
import components.classes.ClassCRUD;
import components.classmember.ClassMember;
import components.classmember.ClassMemberCRUD;
import components.member.Member;
import components.member.MemberAdapter;

public class JoinClassActivity extends AppCompatActivity {
    private SearchView searchView;
    private ListView listView;
    private Class lop;
    private Member member;
    private ClassAdapter2 arrayAdapter;
    private List<Class> classList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_class);
        Intent intent = getIntent();
        member = (Member) intent.getSerializableExtra("member");
        searchView = findViewById(R.id.search_view_class);
        listView = findViewById(R.id.list_class);
        ClassCRUD crud = new ClassCRUD(JoinClassActivity.this);
        ClassMemberCRUD crud2 = new ClassMemberCRUD(JoinClassActivity.this);

        Button btnDone = (Button) findViewById(R.id.btn_done_class);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        classList = new ArrayList<Class>();
        classList = crud.getALlClassMemberNotIn(member);
        arrayAdapter = new ClassAdapter2(classList, member, JoinClassActivity.this);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(JoinClassActivity.this);
                alertDialogBuilder.setMessage("Bán có muốn vào lớp này không");
                alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ClassMember insertClassMem = new ClassMember(member, classList.get(pos), 0);
                        boolean inserted = crud2.insertClassMember(insertClassMem);
                        if (inserted == true){
                            Toast.makeText(JoinClassActivity.this,"Đã vào thành công", Toast.LENGTH_SHORT).show();
                            classList.remove(pos);
                            arrayAdapter.notifyDataSetChanged();
                        }else {
                            classList.remove(pos);
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