package components.membersubmitted;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.plink.R;

import java.util.List;

import components.member.Member;
import components.member.MemberCRUD;

public class MemberSubmittedAdapter extends BaseAdapter {
    //Su dung de hien thi danh sach sinh vien da nop bai
    private Context context;
    private List<MemberSubmitted> memberSubmittedList;

    public MemberSubmittedAdapter(Context context, List<MemberSubmitted> memberSubmittedList) {
        this.context = context;
        this.memberSubmittedList = memberSubmittedList;
    }
    private class ViewHolder{
        TextView name,email;

    }

    @Override
    public int getCount() {
        return memberSubmittedList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return memberSubmittedList.get(i).getMemberid();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.submitted_student_item,null);
            viewHolder = new ViewHolder();
            viewHolder.name = view.findViewById(R.id.membersubmit_name);
            viewHolder.email = view.findViewById(R.id.membersubmit_email);
            view.setTag(viewHolder);
//            view.setBackground(context.getDrawable(R.drawable.listview_selector));
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Member member = new Member();
        member.setId(memberSubmittedList.get(i).getMemberid());
        member = new MemberCRUD(context).getMemberByID(member);
        viewHolder.name.setText(member.getName());
        viewHolder.email.setText(member.getEmail());
        return view;
    }
}
