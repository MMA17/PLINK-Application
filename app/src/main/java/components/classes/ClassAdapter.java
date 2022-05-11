package components.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plink.R;

import java.util.List;

public class ClassAdapter extends BaseAdapter {
    private List<Class> listClass;
    private Context context;

    public ClassAdapter(List<Class> listClass, Context context) {
        this.listClass = listClass;
        this.context = context;
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
        return listClass.get(i).getId();
    }
    private class ViewHolder{
        TextView tvName;
        ImageButton btn1, btn2, btn3;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.home_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tvName = view.findViewById(R.id.class_name);
            viewHolder.btn1 = view.findViewById(R.id.button1);
            viewHolder.btn2 = view.findViewById(R.id.button2);
            viewHolder.btn3 = view.findViewById(R.id.button3);

            view.setTag(viewHolder);

            view.setBackground(context.getDrawable(R.drawable.listview_selector));

        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tvName.setText(listClass.get(i).getName());
        viewHolder.btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(context, "------------" + listClass.get(i).getId() + "------------", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        return view;
    }
}
