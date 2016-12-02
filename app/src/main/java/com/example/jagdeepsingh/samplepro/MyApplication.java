package com.example.jagdeepsingh.samplepro;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.jagdeepsingh.samplepro.RxRetro.model.NetworkService;
import com.example.jagdeepsingh.samplepro.imageCaching.ImageCache;

/**
 * Created by jagdeep.singh on 06-10-2016.
 */

public class MyApplication extends MultiDexApplication {

    ImageCache imageCache;
    private NetworkService networkService;

    @Override
    public void onCreate() {
        super.onCreate();

        networkService = new NetworkService();

        // Creating one time instance of ImageCache
        imageCache = ImageCache.getInstance();
        imageCache.initializeCache();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public NetworkService getNetworkService(){
        return networkService;
    }
}
