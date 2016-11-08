package com.example.jagdeepsingh.samplepro.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

/**
 * Created by Jagdeep.Singh on 07-11-2016.
 */

public class HandlerThread1 extends HandlerThread implements Handler.Callback{

    public static final int MSG_FINISHED = 100;
    public static final int MSG_COUNT_UP = 101;
    public static final int MSG_COUNT_DOWN = 102;

    private Handler handler, callback;

    public HandlerThread1(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    public HandlerThread1(String name, int priority) {
        super(name, priority);
        // TODO Auto-generated constructor stub
    }

    public void setCallback(Handler cb){
        callback = cb;
    }

    @Override
    protected void onLooperPrepared() {
        handler = new Handler(getLooper(), this);
    }

    @Override
    public boolean handleMessage(Message msg) {

        int data1 = msg.arg1;
        int data2 = msg.arg2;
        int counter;

        switch(msg.what){
            case MSG_COUNT_UP:
                for(counter=data1; counter < data2; counter++){
                    //...
                }
                callback.sendMessage(Message.obtain(null, MSG_FINISHED, counter));
                break;
            case MSG_COUNT_DOWN:
                for(counter=data1; counter > data2; counter--){
                    //...
                }
                callback.sendMessage(Message.obtain(null, MSG_FINISHED, counter));
                break;
        }
        return true;
    }

    public void querySomething(int start, int end){
        if(start > end){
            Message msg = Message.obtain(
                    null,   //Handler h
                    MSG_COUNT_DOWN, //int what
                    start,   //int arg1
                    end);   //int arg2
            handler.sendMessage(msg);
        }else if(start < end){
            Message msg = Message.obtain(
                    null,   //Handler h
                    MSG_COUNT_UP, //int what
                    start,   //int arg1
                    end);   //int arg2
            handler.sendMessage(msg);
        }
    }
}