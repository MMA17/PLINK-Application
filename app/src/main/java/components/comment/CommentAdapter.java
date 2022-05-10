package components.comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.plink.R;

import java.util.List;

import components.member.Member;
import components.member.MemberCRUD;
import components.post.Post;

public class CommentAdapter extends BaseAdapter {
    private List<Comment> listComment;
    private Context context;
    private Post post;

    public CommentAdapter(List<Comment> listComment, Context context, Post post) {
        this.listComment = listComment;
        this.context = context;
        this.post = post;
    }

    private class ViewHolder {
        ImageView Commentor_avatar;
        TextView Commentor_name, Commentor_content, Commentor_date;
    }

    public CommentAdapter() {
        super();
    }

    @Override
    public int getCount() {
        return listComment.size();
    }

    @Override
    public Object getItem(int i) {
        return listComment.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listComment.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.comment_item,null);
            viewHolder = new ViewHolder();
            viewHolder.Commentor_avatar = view.findViewById(R.id.Commentor_avatar);
            viewHolder.Commentor_name = view.findViewById(R.id.Commentor_name);
            viewHolder.Commentor_content = view.findViewById(R.id.Commentor_content);
            viewHolder.Commentor_date = view.findViewById(R.id.Commentor_date);

            view.setTag(viewHolder);
            view.setBackground(context.getDrawable(R.drawable.listview_selector));

        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Member m = new Member();
        m.setId(listComment.get(i).getAuthorid());
        m = new MemberCRUD(context).getMemberByID(m);
        viewHolder.Commentor_avatar.setImageResource(R.drawable.avatar2);
        viewHolder.Commentor_name.setText(m.getName());
        viewHolder.Commentor_content.setText(listComment.get(i).getContent());
        viewHolder.Commentor_date.setText(listComment.get(i).getCreated_at());



        return view;
    }
}
