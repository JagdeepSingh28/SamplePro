package com.singh.jagdeep.weblibrary;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

import com.singh.jagdeep.weblibrary.javaInterface.WebAppInterface;

import static com.singh.jagdeep.weblibrary.R.id.webview;

public class LibWebActivity extends Activity implements WebAppInterface.WebViewListener {

    private WebView webView;
    private String webViewArgument;
    private String TAG = LibWebActivity.class.getSimpleName();
    private TextView android_tv;
    private TextView html_context;

    /**
     * Keep the Library Project in Debug Mode and put the release mode AAR file in the other project
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib_web);

        // Enable webview debugging
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        android_tv = (TextView) findViewById(R.id.android_tv);
        html_context = (TextView) findViewById(R.id.html_context);
        webView=(WebView)findViewById(webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebAppInterface(this,this),"Android");

        webViewArgument = "This is from Android Native APP";

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:window.Android.showHTML" +
                        "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");

            }
        });

        String html_string="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h1>My JavaScript</h1>\n" +
                "\n" +
                "<p>JavaScript can change the content of an HTML element:</p>\n" +
                "\n" +
                "<p id=\"demo\">Temp</p>\n" +
                "<input type=\"button\" value=\"Change Text in Android\" onClick=\"Android.showAndroidDialog('Hello Android!')\" />" +
                "<script>\n" +
                ""+
                ""+
                "function toCelsius(f) {\n" +
                "\tdocument.getElementById(\"demo\").innerHTML = f;\n" +
                "}\n" +
                "</script>"+
                "\n" +
                "</body>\n" +
                "</html>\n";

//        webView.loadData(html_string,"text/html",null);
            webView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }

    public void showToastClick(View view) {
        Log.e(TAG, "showToastClick: from Android App" + ((EditText)findViewById(R.id.android_et)).getText().toString());
        webView.loadUrl("javascript:toCelsius('" + ((EditText)findViewById(R.id.android_et)).getText().toString()+ "')");
    }

    @Override
    public void showDialog(final String dialogText) {
//        android_tv.setText(dialogText);
//        new AlertDialog.Builder(this)
//                .setTitle(dialogText)
//                .setMessage("The Dialog text is" + dialogText)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(LibWebActivity.this, dialogText, Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .show();
        Log.e(TAG, "showDialog: method is called" );
        html_context.setText(dialogText);
    }
}
