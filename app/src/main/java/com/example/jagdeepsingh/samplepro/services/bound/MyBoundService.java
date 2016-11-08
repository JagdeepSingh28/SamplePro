package com.example.jagdeepsingh.samplepro.services.bound;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBoundService extends Service {

    public static final String TAG = MyBoundService.class.getSimpleName();

    MyBinder myBinder = new MyBinder();

    public MyBoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return myBinder;
    }

    public class MyBinder extends Binder {
        MyBoundService getService(){
            return MyBoundService.this;
        }
    }

    public void printBoundLog(String log){
        Log.e(TAG, "printBoundLog: "+log);
    }
}
