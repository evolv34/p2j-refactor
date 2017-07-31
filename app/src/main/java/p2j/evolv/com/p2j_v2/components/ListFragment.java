package p2j.evolv.com.p2j_v2.components;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import p2j.evolv.com.p2j_v2.R;
import p2j.evolv.com.p2j_v2.files.FileUtils;

public class ListFragment extends Fragment {
    private FileUtils fileUtils = new FileUtils();

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
        List<File> files = new ArrayList<>();

        try {
            files = fileUtils.files(Environment.getExternalStorageDirectory().getPath());
            Log.i(getClass().getName(), "total files ==> " + files);
        } catch (Exception e) {
            Log.e(getClass().getName(), e.getMessage());
        }
        View view = inflater.inflate(R.layout.p2j_list_fragment, container, false);
        final ListView listview = (ListView) view.findViewById(R.id.list_view);

        ListElementProcessor rows = new ListElementProcessor(getContext(), R.layout.list_element, files);
        listview.setAdapter(rows);

        return view;
    }
}
