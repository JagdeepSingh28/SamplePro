package com.singh.jagdeep.weblibrary.javaInterface;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by Jagdeep.Singh on 26-12-2016.
 */

public class WebAppInterface {

    public interface WebViewListener{
        void showDialog(String dialogText);
    }

    WebViewListener webViewListener;

    Context context;
    private static String TAG = WebAppInterface.class.getSimpleName();

    public WebAppInterface(Context context, WebViewListener webViewListener){
        this.webViewListener = webViewListener;
        this.context = context;
    }

    @JavascriptInterface
    public void showAndroidDialog(String dialogText){
        Log.e(TAG, "dialogText is: " + dialogText );
        Toast.makeText(context, dialogText, Toast.LENGTH_SHORT).show();
//        webViewListener.showDialog(dialogText);
    }

    @JavascriptInterface
    public void showHTML(String html) {
        webViewListener.showDialog(html);
    }
}
