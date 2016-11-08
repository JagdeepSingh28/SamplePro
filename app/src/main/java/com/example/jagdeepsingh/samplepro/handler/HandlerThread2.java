package com.example.jagdeepsingh.samplepro.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

/**
 * Created by Jagdeep.Singh on 07-11-2016.
 */

public class HandlerThread2 extends HandlerThread {

    public static String TAG = HandlerThread2.class.getSimpleName();
    Handler handler;
    public Handler thread2Handler;

    public HandlerThread2(String myLooper2){
        super(myLooper2);
    }

    @Override
    protected void onLooperPrepared() {
        thread2Handler = new Handler(getLooper());
    }

    @Override
    public void run() {

        /**
         * If Above Looper.prepare() method and Looper.Loop() method is not called then
         * following will be thrown
         * "Can't create handler inside thread that has not called Looper.prepare()"
         *
         * So, Looper is required to handle any message from the other thread(UI thread is calling in this case)
         */
        thread2Handler = new Handler(getLooper()){
            /**
             * Message coming from the other thread will be queued in the last
             * @param msg
             */
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                /**
                 * Values coming from Other thread
                 */
                Log.d("Message","Message from UI thread is"+bundle.getString("MSG2_FROM_UI")+"");
                Log.d("Message","Message from First thread is"+msg.arg1+"");
            }
        };

        for (int i = 0; i <10; i++) {
            try {
                sleep(1000);
                Log.d(TAG, "run: -->"+i );

                /**
                 * Sending Message from Thread1 to MainUi thread
                 */
                Bundle bundle = new Bundle();
                bundle.putInt("j",i);
                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
