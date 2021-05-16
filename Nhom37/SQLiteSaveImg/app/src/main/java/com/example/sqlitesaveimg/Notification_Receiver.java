package com.example.sqlitesaveimg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Notification_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("Recive:  ", "mmmmmmmmmmmmmmmmmmmmmmmmmm");

        Intent intent1 = new Intent(context, Notification_Service.class);
        context.startService(intent1);
    }
}
