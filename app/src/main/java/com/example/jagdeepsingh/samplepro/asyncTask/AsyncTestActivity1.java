package com.example.jagdeepsingh.samplepro.asyncTask;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jagdeepsingh.samplepro.R;

public class AsyncTestActivity1 extends AppCompatActivity {

    private static final String TAG = AsyncTestActivity1.class.getSimpleName();
    private TextView textView13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aync_test1);
        textView13 = (TextView) findViewById(R.id.textView13);

        new MyAsyncTask().execute();
    }

    public void StartActivity(View view) {
        finish();
        startActivity(new Intent(AsyncTestActivity1.this,AsyncTaskActivity2.class));
    }

    public class MyAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e(TAG, "onPreExecute: ");
        }

        @Override
        protected Void doInBackground(Void... params) {
            Log.i(TAG, "doInBackground: Start");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "doInBackground: End");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.e(TAG, "onPostExecute: ");
            textView13.setText("IN PosT ExecutE");
        }
    }
}
