package com.example.jagdeepsingh.samplepro.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.jagdeepsingh.samplepro.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
    }
}
