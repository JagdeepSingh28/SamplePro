package com.example.jagdeepsingh.samplepro.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

public class MyIntentService extends IntentService {

    public static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super("MyIntentService");
    }

    public MyIntentService(String name) {
        super(name);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        return 1;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Log.d(TAG, "in onHandleIntent: i --->" );
            /**
             * Pausing the main Thread for 4s by two either methods
             */

//            SystemClock.sleep(4000);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "run: i is -->");
                }
            },5000);


    /**
    * Dummy Thread to check
    */
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                int i = 1;
//                while(i<10)
//                {
//                    try {
////                        if(i==4)
////                            stopSelf();
//                        Thread.sleep(5000);
//                        Log.d(TAG, "in run: i --->" + i);
//                        i++;
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
    }
}
