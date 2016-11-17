package com.example.jagdeepsingh.samplepro;

import com.example.jagdeepsingh.samplepro.RxRetro.model.NetworkService;
import com.example.jagdeepsingh.samplepro.imageCaching.ImageCache;

/**
 * Created by jagdeep.singh on 06-10-2016.
 */

public class MyApplication extends android.app.Application {

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

    public NetworkService getNetworkService(){
        return networkService;
    }
}
