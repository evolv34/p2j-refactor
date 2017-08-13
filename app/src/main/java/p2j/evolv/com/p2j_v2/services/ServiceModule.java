package p2j.evolv.com.p2j_v2.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import java.util.Iterator;
import java.util.Map;

public class ServiceModule {

    public static void start(Class<? extends IntentService> service,
                             Context context,
                             Map<String, Parcelable> properties,
                             ServiceType serviceType) {
        switch (serviceType) {
            case FILE_CONVERSION_SERVICE:
                Iterator<String> propertiesKey = properties.keySet().iterator();
                Intent intent = new Intent(context, service);

                while (propertiesKey.hasNext()) {
                    String key = propertiesKey.next();
                    intent.putExtra(key, properties.get(key));
                }
                context.startService(intent);
                break;
        }
    }
}
