package p2j.evolv.com.p2j_v2.screens.components;

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
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.io.File;

import p2j.evolv.com.p2j_v2.R;
import p2j.evolv.com.p2j_v2.utils.FileUtils;
import p2j.evolv.com.p2j_v2.model.FileDto;

public class ListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private SwipeMenuListView listview = null;

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
        listview = (SwipeMenuListView) view.findViewById(R.id.swipeview);

        try {
            update(Environment.getExternalStorageDirectory().getAbsolutePath());
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage());
        }

        listview.setOnItemClickListener(this);
        listview.setMenuCreator(new FileListMenu.MenuCreator(getContext()));
        listview.setOnMenuItemClickListener(new FileListMenu.OnMenuItemClientListener(this));
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
            String parentPath = new FileUtils(FileUtils.getFileDto().getPath()).getParent();
            update(parentPath);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage());
        }
    }

    public void update(String path) throws Exception {
        FileDto fileDto = new FileUtils(path).files();
        ListElement rows = new ListElement(this.getContext(), R.layout.list_element, fileDto.getFiles());
        listview.setAdapter(rows);
    }
}
