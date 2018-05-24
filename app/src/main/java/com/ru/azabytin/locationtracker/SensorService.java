package com.ru.azabytin.locationtracker;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by fabio on 30/01/2016.
 */
public class SensorService extends Service {
    public int counter=0;

    public SensorService(Context applicationContext) {
        super();
        Log.i("SensorService", "Constructed");
    }


    @Override
    public void onCreate() {
        Log.i("Test", "Service: onCreate");
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_foreground);
        Notification notification = builder.build();

        startForeground(777, notification);
        //Intent hideIntent = new Intent(this, HideNotificationService.class);
        //startService(hideIntent);
    }

    public SensorService() {
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        startTimer();
        Log.i("SensorService", "onStartCommand");
        return START_STICKY;
    }


    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.e("SensorService", "onTaskRemoved!");
        Intent broadcastIntent = new Intent("com.ru.azabytin.locationtracker.RestartSensor");
        sendBroadcast(broadcastIntent);

        super.onTaskRemoved(rootIntent);
        // Destroy the service
        stopSelf();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("SensorService", "onDestroy!");
        Intent broadcastIntent = new Intent("com.ru.azabytin.locationtracker.RestartSensor");
        sendBroadcast(broadcastIntent);

        stoptimertask();
    }

    private Timer timer;
    private TimerTask timerTask;

    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, to wake up every 1 second
        timer.schedule(timerTask, 1000, 1000); //
    }

    /**
     * it sets the timer to print the counter every x seconds 
     */
    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                Log.i("SensorService", "in timer ++++  "+ (counter++));
            }
        };
    }

    /**
     * not needed
     */
    public void stoptimertask() {
        //stop the timer, if it's not already null
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}