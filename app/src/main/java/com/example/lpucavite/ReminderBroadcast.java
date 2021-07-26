package com.example.lpucavite;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReminderBroadcast extends BroadcastReceiver {

    private int datestamp;


    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras = intent.getExtras();
        String eventName = extras.getString("EXTRA_EVENTNAME");
        String eventDate = extras.getString("EXTRA_EVENTDATE");


        try {
            SimpleDateFormat sdf = new SimpleDateFormat("h:mm a EEEE,MMMM dd, yyyy");
            Date date = sdf.parse(eventDate);

            long datelong = date.getTime();
            datestamp = (int) datelong;



        } catch (ParseException e) {
            e.printStackTrace();
        }


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"LySining")
                .setSmallIcon(R.mipmap.test)
                .setContentTitle("LySining Event Reminder")
                .setContentText(eventName + " is about to start!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);



        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);

        notificationManager.notify(datestamp,builder.build());
    }


}
