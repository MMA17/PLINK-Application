package components.member;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plink.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import components.classes.Class;
import components.classmember.ClassMember2Adapter;

public class MemberAdapter extends BaseAdapter {
    private List<Member> memberList;
    private Context ct;
    private Class lop;

    public MemberAdapter(List<Member> memberList, Context ct, Class lop) {
        this.memberList = memberList;
        this.ct = ct;
        this.lop = lop;
    }
    public class ViewHolder{
        TextView tvName, tvPhone;
        ImageView imgView;
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
        MemberAdapter.ViewHolder viewHolder;
        if(view ==null){
            LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.add_class_member_item,null);
            viewHolder = new MemberAdapter.ViewHolder();
            viewHolder.tvName = view.findViewById(R.id.tvName);
            viewHolder.tvPhone = view.findViewById(R.id.tvPhone);
            viewHolder.imgView = view.findViewById(R.id.ivthumbnail);
            view.setTag(viewHolder);
            view.setBackground(ct.getDrawable(R.drawable.listview_selector));
        }
        else{
            viewHolder = (MemberAdapter.ViewHolder) view.getTag();
        }
        viewHolder.imgView.setImageResource(R.drawable.avatar2);
        viewHolder.tvName.setText(memberList.get(i).getName());
        viewHolder.tvPhone.setText(memberList.get(i).getPhone());
        return view;
    }

    public void search(String text){
        text = text.toLowerCase();
        if (text.isEmpty()){
            memberList.clear();
            MemberCRUD crud = new MemberCRUD(ct);
            memberList = crud.getAllMembers();
        } else{
            ArrayList<Member> res = new ArrayList<>();
            for (Member mem : memberList){
                if(mem.getName().toLowerCase().contains(text) ||
                mem.getPhone().toLowerCase().contains(text)){
                    res.add(mem);
                }
            }
            memberList.clear();
            memberList.addAll(res);
        }
        notifyDataSetChanged();
    }
}
