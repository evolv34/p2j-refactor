package p2j.evolv.com.p2j_v2.screens.handlers.refresh;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;

import p2j.evolv.com.p2j_v2.screens.components.ListFragment;
import p2j.evolv.com.p2j_v2.utils.FileUtils;

public class RefreshHandler extends Handler {

    public static final int FILE_REFRESH_CMD = 1;
    private Fragment fragment;

    public RefreshHandler(Fragment listFragment) {
        this.fragment = listFragment;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case FILE_REFRESH_CMD: {
                try {
                    new FileUtils(FileUtils.getFileDto().getPath()).create();
                    String parentPath = new FileUtils(FileUtils.getFileDto().getPath()).getParent();

                    ((ListFragment) fragment).update(parentPath);
                } catch (Exception e) {
                    Log.e(getClass().getName(), "Unable to update files", e);
                }
                break;
            }
        }
    }
}
