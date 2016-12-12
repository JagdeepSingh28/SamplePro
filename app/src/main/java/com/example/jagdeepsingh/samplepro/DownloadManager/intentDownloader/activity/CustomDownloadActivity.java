package com.example.jagdeepsingh.samplepro.DownloadManager.intentDownloader.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jagdeepsingh.samplepro.DownloadManager.intentDownloader.model.File;
import com.example.jagdeepsingh.samplepro.DownloadManager.intentDownloader.service.DownloadingService;
import com.example.jagdeepsingh.samplepro.R;

import static android.os.Build.ID;
import static com.example.jagdeepsingh.samplepro.R.id.textView;


public class CustomDownloadActivity extends AppCompatActivity {

    ListView download_list_view;
    private ArrayAdapter<File> mAdapter;
    private boolean mReceiversRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_download);

        download_list_view = (ListView) findViewById(R.id.download_list_view);

        long id = 0;
        File[] files = {getFile(id++),
                getFile(id++), getFile(id++), getFile(id++),
                getFile(id++), getFile(id++), getFile(id++),
                getFile(id++), getFile(id++), getFile(id++),
                getFile(id++), getFile(id++), getFile(id++),
                getFile(id++), getFile(id++), getFile(id++),
                getFile(id++), getFile(id++), getFile(id++),
                getFile(id++), getFile(id++), getFile(id++),
                getFile(id++), getFile(id++), getFile(id++),
                getFile(id++), getFile(id++), getFile(id)};

        download_list_view.setAdapter(mAdapter = new ArrayAdapter<File>(this,
                R.layout.custom_download_list_item, textView, files) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                updateRow(getItem(position), v);
                return v;
            }
        });

        registerReceiver();
    }

    private void registerReceiver() {
        unregisterReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadingService.PROGRESS_UPDATE_ACTION);

        LocalBroadcastManager.getInstance(this).registerReceiver(mDownloadingProgressReciever,intentFilter);
        mReceiversRegistered = true;
    }

    private void unregisterReceiver() {
        if(mReceiversRegistered){
            LocalBroadcastManager.getInstance(this).unregisterReceiver(mDownloadingProgressReciever);
            mReceiversRegistered = false;
        }
    }

    private File getFile(long id) {
        return new File(id, "http://theopentutorials.com/totwp331/wp-content/uploads/totlogo.png");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
    }

    private void updateRow(final File file, View v) {
        ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        progressBar.setProgress(file.progress);
        TextView    textView    = (TextView) v.findViewById(R.id.textView);
        textView.setText(file.toString());
        Button      cancel      = (Button) v.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code To cancel the download
                Intent i = new Intent();
                i.setAction(DownloadingService.CANCEL_UPDATE_ACTION);
                i.putExtra(ID, file.getId());
                LocalBroadcastManager.getInstance(CustomDownloadActivity.this).sendBroadcast(i);
            }
        });
    }

    // don't call notifyDatasetChanged() too frequently, have a look at
    // following url http://stackoverflow.com/a/19090832/1112882
    protected void onProgressUpdate(int position, int progress) {
        final ListView listView = download_list_view;
        int first = listView.getFirstVisiblePosition();
        int last = listView.getLastVisiblePosition();
        mAdapter.getItem(position).progress = progress > 100 ? 100 : progress;
        if (position < first || position > last) {
            // just update your data set, UI will be updated automatically in next
            // getView() call
        } else {
            View convertView = download_list_view.getChildAt(position - first);
            // this is the convertView that you previously returned in getView
            // just fix it (for example:)
            updateRow(mAdapter.getItem(position), convertView);
        }
    }

    protected void onProgressUpdateOneShot(int[] positions, int[] progresses) {
        for (int i = 0; i < positions.length; i++) {
            int position = positions[i];
            int progress = progresses[i];
            onProgressUpdate(position, progress);
        }
    }

    private final BroadcastReceiver mDownloadingProgressReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(DownloadingService.PROGRESS_UPDATE_ACTION)){
                boolean oneShot = intent.getBooleanExtra("oneshot", false);
                if(oneShot){
                    final int[] progresses = intent
                            .getIntArrayExtra("progress");
                    final int[] positions = intent.getIntArrayExtra("position");
                    onProgressUpdateOneShot(positions, progresses);
                }else{
                    final int progress = intent.getIntExtra("progress", -1);
                    final int position = intent.getIntExtra("position", -1);
                    if (position == -1) {
                        return;
                    }
                    onProgressUpdate(position, progress);
                }
            }
        }
    };
}
