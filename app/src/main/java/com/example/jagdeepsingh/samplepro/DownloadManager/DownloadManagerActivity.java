package com.example.jagdeepsingh.samplepro.DownloadManager;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jagdeepsingh.samplepro.R;

public class DownloadManagerActivity extends AppCompatActivity {

    DownloadManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_manager);
    }

    public void startDownload(View view) {
        dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(
                Uri.parse("http://dl.mytehranmusic.com/1392/Poya/New/1392/7/21/Eminem%20-%20Rap%20God/Eminem%20-%20Rap%20God%20%28CDQ%29%20%5B128%5D.mp3"));
        dm.enqueue(request);
    }

    public void showDownload(View view) {
        Intent i = new Intent();
        i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
        startActivity(i);
    }
}
