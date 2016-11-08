package com.example.jagdeepsingh.samplepro.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class MySimpleService extends Service {
    private static final String TAG = MySimpleService.class.getSimpleName();
    private MediaPlayer mediaPlayer;

    public MySimpleService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: " + "flags is" + flags + "startId is" + startId);

        /**
         * Playing a media file
         * It is running in parallel task
         */
//        try {
//            mediaPlayer = new MediaPlayer();
//            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory()+"/song.mp3");
//            mediaPlayer.prepare();
//            mediaPlayer.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

            /**
             * Pausing the main Thread for 4s by two either methods
             */

            SystemClock.sleep(4000);

//            Handler handler = new Handler(getMainLooper());
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
                    Log.i(TAG, "run: i is -->");
//                }
//            },5000);

        /**
         * Dummy Thread to check
         */
//        new Thread(new Runnable() {
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

//        int onStartReturnFlag = super.onStartCommand(intent, flags, startId);
         int onStartReturnFlag =Service.START_NOT_STICKY;
        //int onStartReturnFlag =Service.START_REDELIVER_INTENT ;
        //int onStartReturnFlag =Service.START_STICKY;

        //int onStartReturnFlag =Service.START_CONTINUATION_MASK;
        //int onStartReturnFlag =Service.START_FLAG_REDELIVERY;
        //int onStartReturnFlag =Service.START_FLAG_RETRY;
        Log.i(TAG, "ServiceInDetail onStartCommand r "+intent+" "+flags+" "+startId +" "+onStartReturnFlag);
        return onStartReturnFlag;
//        return super.onStartCommand(intent, flags, startId);
    }
}
