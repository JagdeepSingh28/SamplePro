package com.example.jagdeepsingh.samplepro.services.bound;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jagdeepsingh.samplepro.R;

public class BoundActivity extends AppCompatActivity {

    MyBoundService myBoundService;
    ServiceConnection serviceConnection;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound);

        findViewById(R.id.print_log).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBound)
                    myBoundService.printBoundLog("Jagdeep");
            }
        });

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyBoundService.MyBinder binder = (MyBoundService.MyBinder) service;
                myBoundService = binder.getService();
                isBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                myBoundService = null;
                isBound = false;
            }
        };

        Intent intent = new Intent(this,MyBoundService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }
}
