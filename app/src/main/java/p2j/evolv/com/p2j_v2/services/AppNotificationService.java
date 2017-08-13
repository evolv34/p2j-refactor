package p2j.evolv.com.p2j_v2.services;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import p2j.evolv.com.p2j_v2.R;
import p2j.evolv.com.p2j_v2.model.FileDto;

public class AppNotificationService {
    private Context context = null;

    public AppNotificationService(Context context) {
        this.context = context;
    }

    public void create(int id, FileDto fileDto) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this.context)
                        .setSmallIcon(R.drawable.p2j)
                        .setContentTitle("1 File Converted")
                        .setContentText(fileDto.getPath())
                        .setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id, mBuilder.build());
    }
}
