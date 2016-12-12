package com.example.jagdeepsingh.samplepro.InternetObserver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.jagdeepsingh.samplepro.R;

import java.util.Observable;
import java.util.Observer;

public class IntenetActivity extends AppCompatActivity implements Observer {

    private static final String TAG = IntenetActivity.class.getSimpleName();
    TextView internet_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intenet);

        internet_tv = (TextView) findViewById(R.id.internet_tv);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NetworkChangeReceiver.getObservable().addObserver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NetworkChangeReceiver.getObservable().deleteObserver(this);
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.i(TAG, "update: ");
        if ((boolean)data){
            internet_tv.setText("Internet is coming");
        }else{
            internet_tv.setText("Internet is not coming");
        }
    }
}