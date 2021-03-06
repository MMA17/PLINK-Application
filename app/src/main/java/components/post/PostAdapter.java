package components.post;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plink.PostActivity;
import com.example.plink.PostDetailActivity;
import com.example.plink.R;

import org.w3c.dom.Text;

import java.util.List;

import components.classes.Class;
import components.classmember.ClassMemberCRUD;
import components.member.Member;
import components.member.MemberCRUD;

public class PostAdapter extends BaseAdapter {
    private List<Post> listPost;
    private Context context;
    private Member member;
    private Class c;
//    private String authorName;

    private class ViewHolder {
        ImageView Post_author_avatar;
        TextView Post_author_position, Post_author, Post_date, Post_content;
    }

    public PostAdapter(List<Post> listPost, Context context, Member member, Class c) {
        this.listPost = listPost;
        this.context = context;
        this.member = member;
        this.c = c;
    }

    @Override
    public int getCount() {
        return listPost.size();
    }

    @Override
    public Object getItem(int i) {
        return listPost.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listPost.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder ViewHolder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.post_item, null);
            ViewHolder = new ViewHolder();
            ViewHolder.Post_author_avatar = view.findViewById(R.id.Post_author_avatar);
            ViewHolder.Post_author = view.findViewById(R.id.Post_author);
            ViewHolder.Post_content = view.findViewById(R.id.Post_content);
            ViewHolder.Post_author_position = view.findViewById(R.id.Post_author_position);
            ViewHolder.Post_date = view.findViewById(R.id.Post_date);

            view.setTag(ViewHolder);
            view.setBackground(context.getDrawable(R.drawable.listview_selector));
        }
        else {
            ViewHolder = (ViewHolder) view.getTag();
        }
        //System.out.println(listPost.get(i).getContent());
        Member m = new Member();
        m.setId(listPost.get(i).getAuthor());
        m =  (new MemberCRUD(context)).getMemberByID(m);

        String authorName = m.getName();

        String position = "Sinh Vi??n";
        ClassMemberCRUD sql = new ClassMemberCRUD(context);
        Member temp = sql.getOwnerfromClass(c.getId());
        if (temp.getId() == m.getId()) {
            position = "Gi??o vi??n";
        }

        ViewHolder.Post_author_avatar.setImageResource(R.drawable.avatar2);
        ViewHolder.Post_author.setText(m.getName());
        ViewHolder.Post_date.setText(listPost.get(i).getCreate_at());
        ViewHolder.Post_author_position.setText(position);
        ViewHolder.Post_content.setText(listPost.get(i).getContent());

        String TempPosition = position;
        ViewHolder.Post_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(context, PostDetailActivity.class);
                in.putExtra("post", listPost.get(i));
                in.putExtra("user", member);
                in.putExtra("authorName", authorName);
                in.putExtra("position", TempPosition);
                context.startActivity(in);
            }
        });

        return view;
    }
}
