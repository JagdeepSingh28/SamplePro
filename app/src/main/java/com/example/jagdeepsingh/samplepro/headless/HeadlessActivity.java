package com.example.jagdeepsingh.samplepro.headless;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jagdeepsingh.samplepro.R;

/**
 * Created by Jagdeep.Singh on 29-07-2016.
 */
public class HeadlessActivity extends Activity implements TaskListener{

    private static final String TAG = HeadlessActivity.class.getSimpleName();
    private TextView headless_tv;
    private ProgressBar progressBar;
    private HeadlessFragment headlessFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.headless_screen);

        headless_tv = (TextView) findViewById(R.id.headless_tv);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if(savedInstanceState != null){
            int progress = savedInstanceState.getInt("progress_value");
            progressBar.setProgress(progress);
        }
        addHeadlessFragment();
        headless_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyAsyncTask myAsyncTask  = new MyAsyncTask(HeadlessActivity.this);
//                myAsyncTask.execute();
                recreate();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("progress_value",progressBar.getProgress());
    }

    @Override
    public void onTaskStarted() {
        progressBar.setVisibility(View.VISIBLE);
        headless_tv.setText("onTaskStarted");
        Log.i(TAG, "onTaskStarted: ");
    }

    @Override
    public void onTaskFinished(String result) {
        /**
         *  After Orientation Change This will cause the following error to occur
         *  DecorView{cfe52bf V.E...... R......D 0,0-684,322} not attached to window manager
         *  as the progressDialog window is attached with the previous Activity which is no more exist
         *  after orientation Change
         */
        progressBar.setVisibility(View.GONE);
        headless_tv.setText(result);
        Log.i(TAG, "onTaskFinished: " + result);
    }

    @Override
    public void onTaskProgressUpdate(int progress){
        progressBar.setProgress(progress);
    }

    void addHeadlessFragment() {
        // Add Headless Fragment to the activity
        headlessFragment = (HeadlessFragment) getFragmentManager()
                .findFragmentByTag(HeadlessFragment.TAG);

        if (headlessFragment == null) {
            headlessFragment = new HeadlessFragment();
            getFragmentManager().beginTransaction()
                    .add(headlessFragment, HeadlessFragment.TAG).commit();
        }
    }

    public void start_task(View view) {
        headlessFragment.startExecutingTask();
    }

    public void cancel_task(View view) {
        headlessFragment.cancelExecutingTask();
    }

    private class MyAsyncTask extends AsyncTask<Void,Void,Void> {

        TaskListener taskListener;

        MyAsyncTask(TaskListener taskListener){
            this.taskListener = taskListener;
        }

        @Override
        protected void onPreExecute() {
            taskListener.onTaskStarted();
        }

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
            taskListener.onTaskFinished("Task Finished");
        }
    }
}
