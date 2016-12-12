package com.example.jagdeepsingh.samplepro.webView;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.jagdeepsingh.samplepro.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        // Enable webview debugging
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        webView=(WebView)findViewById(R.id.webview);

        String html_string="<!DOCTYPE html>\n"
                + "<html>\n"
                + "<body onload=\"myFunction()\">\n"
                + "  Hello World!\n"
                + "  <script>\n"
                + "    function myFunction(){\n"
                + "      console.log(\"I am printed in the console\")\n"
                + "  }\n"
                + "  </script>\n"
                + "</body>\n"
                + "</html>";

        webView.loadData(html_string,"text/html","UTF-8");
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }
}
