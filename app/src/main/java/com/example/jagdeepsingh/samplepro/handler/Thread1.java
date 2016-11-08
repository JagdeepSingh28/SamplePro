package com.example.jagdeepsingh.samplepro.handler;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Jagdeep.Singh on 07-11-2016.
 */

public class Thread1 extends Thread {

    public static String TAG = Thread1.class.getSimpleName();

    Handler         handler;
    public Handler  thread1Handler;
    Context         context;

    public Thread1(Handler handler,Context context) {
        this.handler = handler;
        this.context = context;
    }

    @Override
    public void run() {
        Looper.prepare();

        /**
         * If Above Looper.prepare() method and Looper.Loop() method is not called then
         * following will be thrown
         * "Can't create handler inside thread that has not called Looper.prepare()"
         *
         * So, Looper is required to handle any message from the other thread(UI thread is calling in this case)
         *
         */
        thread1Handler = new Handler(){
            /**
             * Message coming from the other thread will be queued in the last
             * @param msg
             */
            @Override
            public void handleMessage(Message msg) {
                Bundle bundle = msg.getData();
                /**
                 * Values coming from the Other Thread
                 */
                Log.d("Message","Message from UI thread is"+bundle.getString("MSG1_FROM_UI")+"");
                Toast.makeText(context, "Toast from UI thread is", Toast.LENGTH_SHORT).show();
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
                bundle.putInt("i",i);
                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Looper.loop();
    }

    public void sendMessageToThreadB(Message msg3) {
        /**
         * Getting the already running instance from the ThreadActivity
         * and send msg to the Handler of Second Thread
         */
        ((ThreadActivity)context).thread2.thread2Handler.sendMessage(msg3);
    }
}
