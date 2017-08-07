package p2j.evolv.com.p2j_v2.handlers.refresh;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import p2j.evolv.com.p2j_v2.components.ListFragment;
import p2j.evolv.com.p2j_v2.files.FileUtils;

public class RefreshHandler extends Handler {

    public static final int FILE_REFRESH_CMD = 1;
    private ListFragment listFragment;
    private FileUtils fileUtils = new FileUtils();

    public RefreshHandler(ListFragment listFragment) {
        this.listFragment = listFragment;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case FILE_REFRESH_CMD: {
                try {
                    Log.i(getClass().getName(), "File refresh command is called");
                    fileUtils.create(FileUtils.getFileDto().getPath());
                    String parentPath = fileUtils.getParent(FileUtils.getFileDto().getPath());

                    listFragment.update(parentPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
