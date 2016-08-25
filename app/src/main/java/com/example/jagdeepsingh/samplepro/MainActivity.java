package com.example.jagdeepsingh.samplepro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "onCreate: ");

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }
}
