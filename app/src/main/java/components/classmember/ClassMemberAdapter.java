package components.classmember;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;

import com.example.plink.HomeActivity;
import com.example.plink.PostActivity;
import com.example.plink.R;

import java.util.List;

import components.classes.Class;
import components.member.Member;

public class ClassMemberAdapter extends BaseAdapter {
    private List<Class> listClass;
    private Context mContext;
    private Member member;

    public ClassMemberAdapter(List<Class> list, Context mContext, Member member) {
        this.listClass = list;
        this.mContext = mContext;
        this.member = member;
    }

    private class ViewHolder{
        TextView tvName;
        ImageButton btn1, btn2, btn3;
    }

    @Override
    public int getCount() {
        return listClass.size();
    }

    @Override
    public Object getItem(int i) {
        return listClass.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view ==null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.home_item,null);
            viewHolder = new ViewHolder();
            viewHolder.tvName = view.findViewById(R.id.class_name);
            viewHolder.btn1 = view.findViewById(R.id.button1);
            viewHolder.btn2 = view.findViewById(R.id.button2);
            viewHolder.btn3 = view.findViewById(R.id.button3);
            view.setTag(viewHolder);
            view.setBackground(mContext.getDrawable(R.drawable.listview_selector));
        }
        else{
          viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvName.setText(listClass.get(i).getName());
        viewHolder.btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(mContext, "------------" + listClass.get(i).getName() + "------------", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(mContext, PostActivity.class);
                intent.putExtra("user", member);
                intent.putExtra("class", listClass.get(i));
                mContext.startActivity(intent);
            }
        });
        return view;
    }
}
