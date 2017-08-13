package p2j.evolv.com.p2j_v2.screens;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import p2j.evolv.com.p2j_v2.R;
import p2j.evolv.com.p2j_v2.screens.components.ListFragment;
import p2j.evolv.com.p2j_v2.services.AppNotificationService;
import p2j.evolv.com.p2j_v2.utils.FileUtils;
import p2j.evolv.com.p2j_v2.screens.handlers.refresh.RefreshHandler;
import p2j.evolv.com.p2j_v2.services.FileConversionService;
import p2j.evolv.com.p2j_v2.services.ServiceModule;
import p2j.evolv.com.p2j_v2.services.ServiceType;

public class P2J extends AppCompatActivity {
    private Fragment listFragment = ListFragment.newInstance();

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
                    .replace(R.id.root_layout, listFragment, "filesList")
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.global_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                ((ListFragment) listFragment).up();
                return true;
            case R.id.convert:
                if (FileUtils.isValidPath()) {
                    Map<String, Parcelable> properties = new HashMap<>();
                    properties.put("fileDto", FileUtils.getFileDto());

                    ServiceModule.start(FileConversionService.class,
                                        this,
                                        properties,
                                        ServiceType.FILE_CONVERSION_SERVICE);
                    new AppNotificationService(this).create(1, FileUtils.getFileDto());
                    new RefreshHandler((ListFragment) listFragment).sendEmptyMessage(RefreshHandler.FILE_REFRESH_CMD);
                } else {
                    Toast.makeText(getApplicationContext(), "Select a pdf file", Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
