package com.example.jagdeepsingh.samplepro.executor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jagdeepsingh.samplepro.R;
import com.example.jagdeepsingh.samplepro.handler.Thread2;

public class ExecutorActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executor);

        Thread2 thread2 = new Thread2();
        thread2.run();
        thread2.start();
    }

}
