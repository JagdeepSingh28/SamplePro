package com.example.jagdeepsingh.samplepro.eventBus.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.jagdeepsingh.samplepro.R;
import com.example.jagdeepsingh.samplepro.eventBus.POJO;

import org.greenrobot.eventbus.EventBus;

public class DestinationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        POJO pojo = EventBus.getDefault().removeStickyEvent(POJO.class);
        ((TextView)findViewById(R.id.destination_tv)).setText(pojo.string);
    }
}
