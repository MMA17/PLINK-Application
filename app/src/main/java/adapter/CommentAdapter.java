package adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plink.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import components.comment.Comment;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private Context mContext;
    private List<Comment> mData;

    public CommentAdapter(Context mContext, ArrayList<Comment> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(mContext);
        View row = inflater.inflate(R.layout.row_comment,parent,false);
        return  new CommentViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = mData.get(position);
        holder.tv_content.setText(comment.getContent());
//        holder.tv_name.setText(comment.getMember().getName());
        holder.tv_date.setText(comment.getCreated_at());
        holder.img_user.setImageResource(R.drawable.avatar2);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class CommentViewHolder extends RecyclerView.ViewHolder{
        ImageView img_user;
        TextView tv_name,tv_content,tv_date;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            img_user = itemView.findViewById(R.id.user_img);
            tv_name = itemView.findViewById(R.id.comment_username);
            tv_content = itemView.findViewById(R.id.comment_content);
            tv_date = itemView.findViewById(R.id.comment_date);

        }
    }
}
