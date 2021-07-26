package com.example.lpucavite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class eventpopup extends AppCompatActivity {

    private ImageView popview;
    private TextView popname;
    private TextView popdate;
    private TextView popdesc;
    private String eventName;
    private String eventDate;
    private String eventDesc;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventpopup);


        popview = findViewById(R.id.imagepop);
        popname = findViewById(R.id.eventtitle);
        popdate = findViewById(R.id.eventdate);
        popdesc = findViewById(R.id.eventdesc);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String selectedItem = extras.getString("EXTRA_EVENTIMAGE");
        eventName = extras.getString("EXTRA_EVENTNAME");
        eventDate = extras.getString("EXTRA_EVENTDATE");
        eventDesc = extras.getString("EXTRA_EVENTDESC");



        popname.setText(eventName);
        popdate.setText(eventDate);
        popdesc.setText(eventDesc);


        Picasso.get().load(selectedItem)
                .placeholder(R.drawable.placeholder2)
                .fit()
                .centerInside()
                .into(popview);




        ImageButton button = findViewById(R.id.remindme);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                Intent intent = new Intent(eventpopup.this,ReminderBroadcast.class);
                Bundle extras = new Bundle();
                extras.putString("EXTRA_EVENTNAME",eventName);
                extras.putString("EXTRA_EVENTDATE",eventDate);
                intent.putExtras(extras);

                int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(eventpopup.this,m,intent,0);
                AlarmManager alarmManager =(AlarmManager) getSystemService(ALARM_SERVICE);

                long time1 = System.currentTimeMillis();




                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("h:mm a EEEE,MMMM dd, yyyy");
                    Date date = sdf.parse(eventDate);

                    long currenttime = System.currentTimeMillis();
                    long datestamp = date.getTime();

                    if(datestamp > currenttime) {


                        createNotificationChannel();

                        Toast.makeText(eventpopup.this, "Reminder set!", Toast.LENGTH_SHORT).show();

                        alarmManager.set(AlarmManager.RTC_WAKEUP, datestamp,
                                pendingIntent);
                    }

                    else {
                        Toast.makeText(eventpopup.this, "Event is already finished!", Toast.LENGTH_SHORT).show();
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }




            }
        });




    }

    private void createNotificationChannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "LySining";
            String description = "LySining Event Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("LySining",name ,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }



}