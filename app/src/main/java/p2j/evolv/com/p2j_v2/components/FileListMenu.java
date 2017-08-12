package p2j.evolv.com.p2j_v2.components;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import p2j.evolv.com.p2j_v2.R;
import p2j.evolv.com.p2j_v2.files.FileUtils;

public class FileListMenu {
    public static class MenuCreator implements SwipeMenuCreator {
        private Context context;

        public MenuCreator(Context context) {
            this.context = context;
        }

        @Override
        public void create(SwipeMenu menu) {
            // create "open" item
            SwipeMenuItem openItem = new SwipeMenuItem(context);
            // create "delete" item
            SwipeMenuItem deleteItem = new SwipeMenuItem(context);
            // set item background
            deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
            // set item width
            deleteItem.setWidth(dp2px(90));
            // set a icon
            deleteItem.setIcon(R.drawable.ic_action_discard);
            // add to menu
            menu.addMenuItem(deleteItem);
        }


        private int dp2px(int dp) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                    context.getResources().getDisplayMetrics());
        }
    }

    public static class OnMenuItemClientListener implements SwipeMenuListView.OnMenuItemClickListener {
        private Fragment fragment;

        public OnMenuItemClientListener(Fragment fragment) {
            this.fragment = fragment;
        }

        @Override
        public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
            switch (index) {
                case 0:
                    try {
                        String filePath = FileUtils .getFileDto()
                                                    .getFiles()
                                                    .get(position)
                                                    .getAbsolutePath();

                        String parentPath = new FileUtils(filePath).getParent();

                        new FileUtils(filePath).delete();
                        ((ListFragment) fragment).update(parentPath);
                    } catch (Exception e) {
                        Log.e(getClass().getName(), "Invalid file", e);
                    }
                    Log.i(getClass().getName(), "menu item clicked ");
                    break;
            }
            return false;
        }
    }
}
