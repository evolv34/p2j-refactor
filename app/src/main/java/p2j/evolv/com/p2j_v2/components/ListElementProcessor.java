package p2j.evolv.com.p2j_v2.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import p2j.evolv.com.p2j_v2.R;

public class ListElementProcessor extends ArrayAdapter<String> {

    Context context;
    LayoutInflater inflater = null;
    List<File> fileNameList;

    public ListElementProcessor(Context context, int resource, List<File> file_name_list) {
        super(context, resource);
        this.context = context;
        this.fileNameList = file_name_list;
    }

    @Override
    public int getCount() {
        return this.fileNameList.size();
    }

    @Override
    public String getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_element, parent, false);
        }

        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
        TextView fileNameView = (TextView) convertView.findViewById(R.id.file_name);
        TextView fileDetails = (TextView) convertView.findViewById(R.id.file_details);

        File file = fileNameList.get(position);
        String fileName = file.getName();
        fileNameView.setText(fileName);
        fileDetails.setText(String.valueOf(file.length()));

        if (!(fileName.endsWith(".pdf") || fileName.endsWith(".jpg"))) {
            icon.setImageResource(R.drawable.folder);
        } else if (fileName.endsWith(".pdf")) {
            icon.setImageResource(R.drawable.pdf_icon);
        } else if (fileName.endsWith(".jpg")) {
            icon.setImageResource(R.drawable.jpg);
        }
        return convertView;
    }
}
