package com.example.jagdeepsingh.samplepro.InternetObserver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jagdeepsingh.samplepro.MyApplication;
import com.example.jagdeepsingh.samplepro.R;

import java.util.Observable;
import java.util.Observer;

public class IntenetActivity extends AppCompatActivity implements Observer {

    private static final String TAG = IntenetActivity.class.getSimpleName();
    TextView internet_tv;
    MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intenet);

        internet_tv = (TextView) findViewById(R.id.internet_tv);
        internet_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myApplication.getNetworkObservable().connectionChanged(true);
            }
        });
        myApplication = (MyApplication) getApplication();
        myApplication.getNetworkObservable().addObserver(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: Adding observer" );
//        NetworkChangeReceiver.getObservable().addObserver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onResume: Deleting observer" );
//        NetworkChangeReceiver.getObservable().deleteObserver(this);
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.i(TAG, "update: ");
        Toast.makeText(this, "I am notified",Toast.LENGTH_SHORT).show();
        if ((boolean)data){
            internet_tv.setText("Internet is coming");
        }else{
            internet_tv.setText("Internet is not coming");
        }
    }
}