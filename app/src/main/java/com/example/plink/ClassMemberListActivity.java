package com.example.plink;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import components.classmember.ClassMember;
import components.classmember.ClassMemberCRUD;
import components.classes.Class;
import components.member.Member;


public class ClassMemberListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_members_list);
        Class lop = new Class();
        ListView lv = (ListView) findViewById(R.id.listview);

        ClassMemberCRUD crud = new ClassMemberCRUD(ClassMemberListActivity.this);
//        List<Member> memberList = crud.getMemberfromClass(lop);
//        List<String> nameList = new ArrayList<>();
//        for (int i = 0; i <= memberList.size(); i++){
//            nameList.add(memberList.get(i).getName());
//        }
        String[] locations = {"Hồ tây", "Tháp Rùa", "Chùa một cột", "Quốc tử giám", "Lăng Bác", "Thư viện QG"};
        lv.setAdapter(new MyListAdapter(this, R.layout.class_member_list_row, Arrays.asList(locations)));

    }
    private class MyListAdapter extends ArrayAdapter<String>{
        private int layout;

        private MyListAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
            super(context, resource, objects);
            layout = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder mainViewHolder = null;
            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(layout, parent, false);
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.list_item_thumbnail);
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_textView);
                viewHolder.button = (Button) convertView.findViewById(R.id.list_item_button);
                viewHolder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), "Button was clicked" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                convertView.setTag(viewHolder);
            }
            else{
                mainViewHolder = (ViewHolder) convertView.getTag();
                mainViewHolder.title.setText(getItem(position));
            }

            return convertView;
        }
    }
    public class ViewHolder{
        ImageView thumbnail;
        TextView title;
        Button button;
    }
}