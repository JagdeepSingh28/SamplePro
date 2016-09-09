package com.example.jagdeepsingh.samplepro.wear;

import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jagdeepsingh.samplepro.R;

public class SelfNotificationActivity extends AppCompatActivity {

    int notificationId = 001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_notification);

    }

    public void sendNotification(View view){
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(SelfNotificationActivity.this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Title")
                        .setContentText("Android Wear Notification");

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(SelfNotificationActivity.this);

        notificationManager.notify(notificationId, notificationBuilder.build());
    }
}
