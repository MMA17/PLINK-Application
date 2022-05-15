package components.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plink.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import components.member.Member;
import components.member.MemberAdapter;
import components.member.MemberCRUD;

public class ClassAdapter2 extends BaseAdapter {
    private List<Class> listClass;
    private Context ct;
    private Member mem;

    public ClassAdapter2(List<Class> listClass,Member mem, Context ct) {
        this.listClass = listClass;
        this.ct = ct;
        this.mem = mem;
    }

    public class ViewHolder{
        TextView tvCName, tvCID;
        ImageView ivCthumbnail;
    }

    @Override
    public int getCount() {
        return listClass.size();
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
        ViewHolder viewHolder;
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) ct.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
            view = layoutInflater.inflate(R.layout.join_class_row, null);
            viewHolder = new ViewHolder();
            viewHolder.tvCName = view.findViewById(R.id.tvCName);
            viewHolder.tvCID = view.findViewById(R.id.tvCID);
            viewHolder.ivCthumbnail = view.findViewById((R.id.ivCthumbnail));
            view.setTag(viewHolder);
            view.setBackground(ct.getDrawable(R.drawable.listview_selector));
        } else {
            viewHolder = (ClassAdapter2.ViewHolder) view.getTag();
        }
        viewHolder.ivCthumbnail.setImageResource(R.drawable.book);
        viewHolder.tvCName.setText(listClass.get(i).getName());
        viewHolder.tvCID.setText(String.valueOf(listClass.get(i).getId()));
        return view;
    }
    public void search(String text){
        text = text.toLowerCase();
        if (text.isEmpty()){
            listClass.clear();
            ClassCRUD crud = new ClassCRUD(ct);
            listClass = crud.getALlClassMemberNotIn(mem);
        } else{
            ArrayList<Class> res = new ArrayList<>();
            for (Class lop : listClass){
                String id = Integer.toString(lop.getId());
                if(lop.getName().toLowerCase().contains(text) ||
                        id.contains(text)){
                    res.add(lop);
                }
            }
            listClass.clear();
            listClass.addAll(res);
        }
        notifyDataSetChanged();
    }
}
