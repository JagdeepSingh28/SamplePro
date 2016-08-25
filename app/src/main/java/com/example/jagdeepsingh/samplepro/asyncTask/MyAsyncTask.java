package com.example.jagdeepsingh.samplepro.asyncTask;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;

/**
 * Created by Jagdeep.Singh on 26-07-2016.
 */
public class MyAsyncTask extends AsyncTask<Void,Void,Void> {

    private static final String TAG = MyAsyncTask.class.getSimpleName();
    private String currentThreadName;

    MyAsyncTask(String currentThreadName){
        this.currentThreadName = currentThreadName;
    }

    @Override
    protected Void doInBackground(Void... params) {
        for (int i = 0; i < 5; i++) {
            try {
                Log.i(TAG, "doInBackground: start" + i + Thread.currentThread().getName());
                SystemClock.sleep(5000);
                Log.i(TAG, "doInBackground: end" + i + Thread.currentThread().getName());
            }catch(Exception e){
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return currentThreadName;
    }
}
