package com.example.jagdeepsingh.samplepro.services;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.jagdeepsingh.samplepro.R;

public class MyServiceActivity extends AppCompatActivity {

    private static final String TAG = MyServiceActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);

    }

    public void simpleService(View view) {
        Intent intent = new Intent(this,MySimpleService.class);
        startService(intent);
    }

    public void intentService(View view) {
        Intent intent = new Intent(this,MyIntentService.class);
        startService(intent);
    }

    public void stopService(View view) {
        Intent intent = new Intent(this,MySimpleService.class);
        stopService(intent);
    }


    public void IsServiceRunning(View view) {
        Log.e(TAG, "IsServiceRunning: " + isMyServiceRunning(MySimpleService.class));
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void IsIntentServiceRunning(View view) {
        Log.e(TAG, "IsIntentServiceRunning: " + isMyServiceRunning(MyIntentService.class));
    }
}
