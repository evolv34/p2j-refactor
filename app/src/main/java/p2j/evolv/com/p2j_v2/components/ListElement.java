package p2j.evolv.com.p2j_v2.components;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

import p2j.evolv.com.p2j_v2.R;
import p2j.evolv.com.p2j_v2.files.FileType;
import p2j.evolv.com.p2j_v2.files.FileUtils;

public class ListElement extends ArrayAdapter<File> {

    Context context;
    List<File> files;

    public ListElement(Context context, int resource, List<File> files) {
        super(context, resource);
        this.context = context;
        this.files = files;
    }

    @Override
    public int getCount() {
        return this.files.size();
    }

    @Override
    public File getItem(int position) {
        return this.files.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_element, parent, false);
        }

        ImageView icon = (ImageView) convertView.findViewById(R.id.icon);
        TextView fileNameView = (TextView) convertView.findViewById(R.id.file_name);
        TextView fileDetails = (TextView) convertView.findViewById(R.id.file_details);

        File file = files.get(position);
        String fileName = file.getName();
        fileNameView.setText(fileName);

        Long filesize = new FileUtils(file.getPath()).size();
        fileDetails.setText(FileUtils.Conversions.convert(filesize));

        if (file.isDirectory()) {
            icon.setImageResource(R.drawable.folder);
        } else if (fileName.endsWith(FileType.PDF.fileType())) {
            icon.setImageResource(R.drawable.pdf_icon);
        } else if (fileName.endsWith(FileType.JPEG.fileType())) {
            icon.setImageResource(R.drawable.jpg);
        }
        return convertView;
    }
}
