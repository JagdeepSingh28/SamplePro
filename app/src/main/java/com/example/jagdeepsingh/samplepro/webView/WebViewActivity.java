package com.example.jagdeepsingh.samplepro.webview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.jagdeepsingh.samplepro.R;
import com.singh.jagdeep.weblibrary.LibWebActivity;

public class WebViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
    }

    public void showSDKDialog(View view){
        startActivity(new Intent(this, LibWebActivity.class));
    }
}
