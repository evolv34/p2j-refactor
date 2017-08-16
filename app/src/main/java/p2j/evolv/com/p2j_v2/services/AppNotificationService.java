package p2j.evolv.com.p2j_v2.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import p2j.evolv.com.p2j_v2.R;
import p2j.evolv.com.p2j_v2.model.FileDto;
import p2j.evolv.com.p2j_v2.screens.P2J;

public class AppNotificationService {
    private Context context = null;

    public AppNotificationService(Context context) {
        this.context = context;
    }

    public void create(int id, FileDto fileDto) {

        Intent notificationIntent = new Intent(context, P2J.class);
        notificationIntent.putExtra("path", fileDto.getPath());
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this.context)
                        .setSmallIcon(R.drawable.p2j)
                        .setContentTitle("1 File Converted")
                        .setContentText(fileDto.getPath())
                        .setAutoCancel(true)
                        .setContentIntent(intent);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(id, mBuilder.build());
    }
}
