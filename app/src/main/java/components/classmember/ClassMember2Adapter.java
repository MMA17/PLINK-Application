package components.classmember;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.plink.ClassMemberListActivity;
import com.example.plink.R;

import java.util.List;

import components.member.Member;
import components.classes.Class;

public class ClassMember2Adapter extends BaseAdapter {
    private List<Member> memberList;
    private Context ct;
    private Class lop;

    public ClassMember2Adapter(List<Member> memberList, Context ct, Class lop) {
        this.memberList = memberList;
        this.ct = ct;
        this.lop = lop;
    }
    private class ViewHolder{
        TextView tvName;
        ImageView imgView;
        ImageButton btn1;
    }


    @Override
    public int getCount() {
        return memberList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ClassMember2Adapter.ViewHolder viewHolder;
        if(view ==null){
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.class_member_list_row,null);
            viewHolder = new ClassMember2Adapter.ViewHolder();
            viewHolder.tvName = view.findViewById(R.id.list_item_textView);
            viewHolder.btn1 = view.findViewById(R.id.list_item_button);
            viewHolder.imgView = view.findViewById(R.id.list_item_thumbnail);
            view.setTag(viewHolder);
            view.setBackground(ct.getDrawable(R.drawable.listview_selector));
        }
        else{
            viewHolder = (ClassMember2Adapter.ViewHolder) view.getTag();
        }
        viewHolder.imgView.setImageResource(R.drawable.avatar);
        viewHolder.tvName.setText(memberList.get(i).getName());
        viewHolder.btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ct);
                alertDialogBuilder.setMessage("Bán có muốn xóa thành phần này!");
                alertDialogBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int iiiii) {
                            ClassMemberCRUD crud = new ClassMemberCRUD(ct);
                            ClassMember deleteClassMem = new ClassMember(memberList.get(i), lop, 0);
                            crud.deleteClassMember(deleteClassMem);
                            memberList.remove(i);
                            notifyDataSetChanged();
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
        return view;
    }
}
