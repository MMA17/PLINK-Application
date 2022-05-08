package components.post;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plink.R;

import org.w3c.dom.Text;

import java.util.List;

public class PostAdapter extends BaseAdapter {
    private List<Post> listPost;
    private Context context;

    private class ViewHolder {
        ImageView Post_author_avatar;
        TextView Post_author_position, Post_author, Post_date, Post_content;
    }

    public PostAdapter(List<Post> listPost, Context context) {
        this.listPost = listPost;
        this.context = context;
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
        ViewHolder.Post_author_avatar.setImageResource(R.drawable.avatar);
        ViewHolder.Post_author.setText(String.valueOf(listPost.get(i).getAuthor()));
        ViewHolder.Post_date.setText(listPost.get(i).getCreate_at());
        ViewHolder.Post_author_position.setText(String.valueOf(listPost.get(i).getAuthor()));
        ViewHolder.Post_content.setText(listPost.get(i).getContent());

        return view;
    }
}
