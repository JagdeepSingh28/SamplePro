package com.example.jagdeepsingh.samplepro.headless;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.jagdeepsingh.samplepro.R;

/**
 * Created by Jagdeep.Singh on 29-07-2016.
 */
public class HeadlessActivity extends Activity {

    private static final String TAG = HeadlessActivity.class.getSimpleName();

    TextView headless_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.headless_screen);

        headless_tv = (TextView) findViewById(R.id.headless_tv);

        headless_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyAsyncTask().execute();
            }
        });

    }

    private class MyAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Log.i(TAG, "doInBackground:  before sleep");
                Thread.sleep(5000);
                Log.i(TAG, "doInBackground:  After sleep");
            }catch(InterruptedException e){
                Log.i(TAG, "doInBackground: ");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            headless_tv.setText("New Text");
        }
    }
}
