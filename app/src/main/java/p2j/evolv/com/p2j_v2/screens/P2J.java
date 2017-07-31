package p2j.evolv.com.p2j_v2.screens;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import p2j.evolv.com.p2j_v2.R;
import p2j.evolv.com.p2j_v2.components.ListFragment;

public class P2J extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p2j);

        final int permission = ActivityCompat.checkSelfPermission((Activity) this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.root_layout, ListFragment.newInstance(), "filesList")
                    .commit();
        }
//        P2jBinding binding = DataBindingUtil.setContentView(this, R.layout.p2j);
//        ListElementDto user = new ListElementDto("Pradeep");
////
//        binding.
//        binding.setUser(user);
    }
}
