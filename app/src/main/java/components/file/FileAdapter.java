package components.file;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.plink.R;

import java.util.List;

public class FileAdapter extends BaseAdapter {
    private List<File> listFiles;
    private Context context;

    public FileAdapter(List<File> listFiles, Context context) {
        this.listFiles = listFiles;
        this.context = context;
    }

    public FileAdapter() {
        super();
    }

    private class ViewHolder {
        Button file;
    }

    @Override
    public int getCount() {
        return listFiles.size();
    }

    @Override
    public Object getItem(int i) {
        return listFiles.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listFiles.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FileAdapter.ViewHolder ViewHolder = new ViewHolder();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.file_item, null);

            ViewHolder.file = view.findViewById(R.id.file);

            view.setTag(ViewHolder);
            view.setBackground(context.getDrawable(R.drawable.listview_selector));

        }
        else {
            ViewHolder = (FileAdapter.ViewHolder) view.getTag();
        }

        ViewHolder.file.setText(listFiles.get(i).getName());
        ViewHolder.file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(listFiles.get(i).getPath()));
                context.startActivity(browserIntent);
            }
        });

        return view;
    }
}
