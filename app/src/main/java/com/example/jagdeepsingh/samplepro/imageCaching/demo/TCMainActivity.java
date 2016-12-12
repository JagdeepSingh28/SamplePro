package com.example.jagdeepsingh.samplepro.imageCaching.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.jagdeepsingh.samplepro.R;

public class TCMainActivity extends AppCompatActivity {

    ImageView imageView_cache;
    TCImageLoader tcImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcmain);

        imageView_cache = (ImageView) findViewById(R.id.imageView_cache);
        tcImageLoader   = new TCImageLoader(this);
        tcImageLoader.display("http://api.androidhive.info/images/glide/small/deadpool.jpg"
        ,imageView_cache,R.drawable.common_google_signin_btn_icon_light);
    }

    public void loadAgain(View view) {
        tcImageLoader.display("http://api.androidhive.info/images/glide/small/deadpool.jpg"
                ,imageView_cache,R.drawable.common_google_signin_btn_icon_light);
    }
}
