package com.ru.azabytin.locationtracker;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class HideNotificationService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_foreground);
        Notification notification = notification = builder.build();;

        startForeground(777, notification);
        stopForeground(true);
    }

}
