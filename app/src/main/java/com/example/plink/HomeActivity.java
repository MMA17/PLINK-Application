package com.example.plink;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import components.classes.Class;
import components.classes.ClassAdapter;
import components.classes.ClassCRUD;
import components.classmember.ClassMember;
import components.classmember.ClassMember2Adapter;
import components.classmember.ClassMemberAdapter;
import components.classmember.ClassMemberCRUD;
import components.member.Member;

public class HomeActivity extends AppCompatActivity {

    private ListView listView;
    private List<Class> listClass = new ArrayList<>();
    private ClassMemberAdapter adapter;
    private ClassMemberCRUD sqliHelper;
    private FloatingActionButton fab, fab2, fab3;
    private Member member;
    private boolean isFABOpen =false;
    private TextView tvJoin, tvCreate,txtHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent i = getIntent();
        member = (Member) i.getSerializableExtra("Member");
        fab = findViewById(R.id.fab);
        fab2 = findViewById(R.id.fab2);
        fab3 = findViewById(R.id.fab3);
        tvJoin = findViewById(R.id.textview_join);
        tvCreate = findViewById(R.id.textview_create);
        txtHome = findViewById(R.id.txtHomeActivity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        listView = findViewById(R.id.lv_class);

        sqliHelper = new ClassMemberCRUD(HomeActivity.this);
        initClassList();
        init(fab2);
        init(fab3);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFABOpen = rotateFab(view, !isFABOpen);
                if(isFABOpen){
                    showIn(fab2);
                    showIn(tvJoin);
                    showIn(fab3);
                    showIn(tvCreate);
                }else{
                    showOut(fab2);
                    showOut(tvJoin);
                    showOut(tvCreate);
                    showOut(fab3);
                }

            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CreateClassActivity.class);
                intent.putExtra("user", member);
                startActivity(intent);
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, JoinClassActivity.class);
                intent.putExtra("member", member);
                startActivity(intent);
            }
        });
    }
    private void initClassList(){
        listClass = sqliHelper.getClassbyMember(member,HomeActivity.this);
        adapter = new ClassMemberAdapter(listClass, HomeActivity.this, member);
        listView.setAdapter(adapter);
//        Xoa lop

//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
//                alertDialogBuilder.setMessage("Bán có muốn thêm người dùng này vào lớp không");
//                alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        System.out.println(pos);
//                        ClassMember insertClassMem = new ClassMember(listClass.get(pos), lop, 0);
//                        boolean inserted = crud.insertClassMember(insertClassMem);
//                        if (inserted == true){
//                            Toast.makeText(HomeActivity.this,"Đã thêm thành công", Toast.LENGTH_SHORT).show();
//                            listClass.remove(pos);
//                            adapter.notifyDataSetChanged();
//                        }else {
//                            listClass.remove(pos);
//                            adapter.notifyDataSetChanged();
//                        }
//                    }
//                });
//            }
//        });
        adapter.notifyDataSetChanged();
        if(listClass.size() <=0){

            txtHome.setText("Chua tham gia lop nao");
            txtHome.setVisibility(View.VISIBLE);
        }
        else{
            txtHome.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.bell){
            Toast.makeText(HomeActivity.this,"Dang xuat",Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Intent i = new Intent(this,ProfileActivity.class);
            i.putExtra("Member",member);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        ClassMemberCRUD crud = new ClassMemberCRUD(HomeActivity.this);
        initClassList();
    }

    //các animation cho fab
    public static boolean rotateFab(final View v, boolean rotate) {
        v.animate().setDuration(200)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                })
                .rotation(rotate ? 180f : 0f);
        return rotate;
    }
    public static void showIn(final View v) {
        v.setVisibility(View.VISIBLE);
        v.setAlpha(0f);
        v.setTranslationY(v.getHeight());
        v.animate()
                .setDuration(200)
                .translationY(0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                    }
                })
                .alpha(1f)
                .start();
    }
    public static void showOut(final View v) {
        v.setVisibility(View.VISIBLE);
        v.setAlpha(1f);
        v.setTranslationY(0);
        v.animate()
                .setDuration(200)
                .translationY(v.getHeight())
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        v.setVisibility(View.GONE);
                        super.onAnimationEnd(animation);
                    }
                }).alpha(0f)
                .start();
    }

    public static void init(final View v) {
        v.setVisibility(View.GONE);
        v.setTranslationY(v.getHeight());
        v.setAlpha(0f);
    }
}

