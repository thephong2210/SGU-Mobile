package com.example.sqlitesaveimg;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;

public class Notification_Service extends Service {
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    public void onCreate() {
        super.onCreate();
        this.notificationManagerCompat = NotificationManagerCompat.from(this);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("onStartCommand: ", "cmmmmmmmmmmmmmmmmmmm");
        sendNoti();
        return START_NOT_STICKY;
    }


    private void sendNoti() {

        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.notification_vang)
                .setContentTitle("Selfie")
                .setContentText("Selfie làm bức hình nền ...")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null)
            notificationManager.notify(1, notification);
    }
}
