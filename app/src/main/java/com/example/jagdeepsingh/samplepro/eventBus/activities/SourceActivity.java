package com.example.jagdeepsingh.samplepro.eventBus.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jagdeepsingh.samplepro.R;
import com.example.jagdeepsingh.samplepro.eventBus.POJO;

import org.greenrobot.eventbus.EventBus;

public class SourceActivity extends AppCompatActivity {

    private POJO pojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);

         pojo = new POJO(1,"Jagdeep Singh",true,1.0f);
        ((TextView)findViewById(R.id.source_tv)).setText(pojo.string);

    }



    public void sendButton(View view) {
        Intent intent = new Intent(SourceActivity.this,DestinationActivity.class);
        EventBus.getDefault().postSticky(pojo);
        startActivity(intent);
    }
}
