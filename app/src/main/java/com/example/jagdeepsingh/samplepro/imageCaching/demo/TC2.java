package com.example.jagdeepsingh.samplepro.imageCaching.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jagdeepsingh.samplepro.R;

public class TC2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tc2);
    }

    public void goToAnother(View view) {
        startActivity(new Intent(TC2.this,TCMainActivity.class));
    }
}
