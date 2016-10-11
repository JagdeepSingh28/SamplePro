package com.example.jagdeepsingh.samplepro;

import com.example.jagdeepsingh.samplepro.imageCaching.ImageCache;

/**
 * Created by jagdeep.singh on 06-10-2016.
 */

public class MyApplication extends android.app.Application {

    ImageCache imageCache;

    @Override
    public void onCreate() {
        super.onCreate();
        // Creating one time instance of ImageCache
        imageCache = ImageCache.getInstance();
        imageCache.initializeCache();
    }
}
