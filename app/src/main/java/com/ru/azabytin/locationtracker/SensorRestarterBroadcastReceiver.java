package com.ru.azabytin.locationtracker;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SensorRestarterBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("SensorRestarterBroadcastReceiver", "onReceive() start service");
        context.startService(new Intent(context, SensorService.class));
    }
}