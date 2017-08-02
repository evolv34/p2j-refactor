package p2j.evolv.com.p2j_v2.components;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;

import p2j.evolv.com.p2j_v2.R;
import p2j.evolv.com.p2j_v2.files.FileUtils;
import p2j.evolv.com.p2j_v2.model.FileDto;

public class ListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private FileUtils fileUtils = new FileUtils();
    private ListView listview = null;
    private boolean isUpdateEnable = false;

    public static Fragment newInstance() {
        return new ListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.p2j_list_fragment, container, false);
        listview = (ListView) view.findViewById(R.id.list_view);

        try {
            update(Environment.getExternalStorageDirectory().getAbsolutePath());
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage());
        }
        listview.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        select(view);
        File file = (File) parent.getAdapter().getItem(position);
        try {
            update(file.getPath());
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage());
        }
    }

    private void select(View view) {
        view.setBackgroundColor(Color.rgb(50, 80, 109));
        ((TextView) view.findViewById(R.id.file_name)).setTextColor(Color.WHITE);
        ((TextView) view.findViewById(R.id.file_details)).setTextColor(Color.WHITE);
    }

    public void up() {
        try {
            String parentPath = fileUtils.getParent(FileUtils.getFileDto().getPath());
            update(parentPath);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage());
        }
    }

    public void update(String path) throws Exception {
        FileDto fileDto = fileUtils.files(path);
        ListElement rows = new ListElement(this.getContext(), R.layout.list_element, fileDto.getFiles());
        listview.setAdapter(rows);
    }
}
