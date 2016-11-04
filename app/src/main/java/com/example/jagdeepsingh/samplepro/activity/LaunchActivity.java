package com.example.jagdeepsingh.samplepro.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jagdeepsingh.samplepro.R;

public class LaunchActivity extends AppCompatActivity {

    private int NOTIFICATION_ID = 0;
    Notification notification ;
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        createTaskStackBuilderNotification();

    }

    private void createTaskStackBuilderNotification() {
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);

        /**
         * This will go in a stack like
         *  | intentDetailActivity  |
         *  | intentListActivity    |
         *  | intentLaunchActivity  |
         *
         *  So after launching DetailActivity it will open the previous
         *  two activities on Back Press
         */
        Intent intentLaunchActivity   = new Intent(this, LaunchActivity.class);
        Intent intentListActivity   = new Intent(this, ListActivity.class);
        Intent intentDetailActivity = new Intent(this, DetailActivity.class);

        taskStackBuilder.addNextIntent(intentLaunchActivity);
        taskStackBuilder.addNextIntent(intentListActivity);
        taskStackBuilder.addNextIntent(intentDetailActivity);

        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Sample Pro")
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(pendingIntent)
                .setContentText("text")
                .build();

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void Show_noti(View view) {
        notificationManager.notify(NOTIFICATION_ID,notification);
    }
}
