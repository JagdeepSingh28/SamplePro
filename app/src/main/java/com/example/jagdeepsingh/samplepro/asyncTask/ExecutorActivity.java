package com.example.jagdeepsingh.samplepro.asyncTask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import com.example.jagdeepsingh.samplepro.R;

/**
 * Created by Jagdeep.Singh on 26-07-2016.
 */
public class ExecutorActivity extends AppCompatActivity{

    private static final String TAG = ExecutorActivity.class.getSimpleName();
    Button btn;
    Toolbar toolbar;
    android.support.v7.app.ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.executor_screen);

        btn = (Button) findViewById(R.id.btn);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        actionBar.setDisplayShowHomeEnabled(true);

        for (int i = 0; i < 5; i++) {
            Log.i(TAG, "onCreate: ");
//            new MyAsyncTask("Thread " + i).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new MyAsyncTask("Thread " + i).execute();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
