package components.homework;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plink.ExcerciseDetailActivity;
import com.example.plink.R;

import java.util.List;

import components.member.Member;

public class HomeworkAdapter extends BaseAdapter {
    private List<Homework> homeworkList;
    private Context mContext;
    private Member author,member;
    public HomeworkAdapter(List<Homework> homeworkList, Context mContext, Member author,Member member) {
        this.homeworkList = homeworkList;
        this.mContext = mContext;
        this.author = author;
        this.member = member;
    }

    private class ViewHolder{
        ImageView excercise_author;
        TextView author_name,positon,excercise_createdat,excercise_content;
    }
    @Override
    public int getCount() {
        return homeworkList.size();
    }

    @Override
    public Object getItem(int i) {
        return homeworkList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return homeworkList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.excercise_item,null);
            viewHolder = new ViewHolder();
            viewHolder.excercise_author = view.findViewById(R.id.excercise_author);
            viewHolder.author_name = view.findViewById(R.id.author_name);
            viewHolder.positon = view.findViewById(R.id.author_role);
            viewHolder.excercise_createdat = view.findViewById(R.id.excercise_createdat);
            viewHolder.excercise_content = view.findViewById(R.id.excercise_content);
            view.setTag(viewHolder);
            view.setBackground(mContext.getDrawable(R.drawable.listview_selector));

        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.excercise_content.setText(homeworkList.get(i).getContent());
        viewHolder.author_name.setText(author.getName());
        viewHolder.excercise_createdat.setText(homeworkList.get(i).getCreate_at());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ExcerciseDetailActivity.class);
                intent.putExtra("member",member);
                intent.putExtra("excercise",homeworkList.get(i));
                intent.putExtra("author",author);
                mContext.startActivity(intent);
            }
        });
        return view;
    }
}
