package com.example.jagdeepsingh.samplepro.imageCaching;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

/**
 * Created by jagdeep.singh on 05-10-2016.
 */

public class ImageCache {

    private static final String         TAG = ImageCache.class.getSimpleName();
    private static ImageCache           imageCache;
    private LruCache<String,Bitmap>     lruCache;

    public static ImageCache getInstance(){
        if(imageCache == null){
            imageCache = new ImageCache();
        }
        return imageCache;
    }

    public void initializeCache(){
        final int maxMemory = (int)Runtime.getRuntime().maxMemory()/1024;
        final int cacheSize = maxMemory/8;

        Log.i(TAG, "initializeCache: is" + cacheSize);

        lruCache = new LruCache<String, Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int bitmapByteCount = value.getRowBytes() * value.getHeight();
                return bitmapByteCount/1024;
            }
        };
    }

    public void addImageToCache(String key, Bitmap bitmap){
        if(lruCache != null && lruCache.get(key) == null){
            lruCache.put(key,bitmap);
        }
    }

    public Bitmap getImageFromCahce(String key){
        if(lruCache != null){
            return lruCache.get(key);
        }
        return null;
    }

    public void removeImageFromCache(String key){
        if(lruCache != null){
            lruCache.remove(key);
        }
    }

    public void clearCache(){
        if(lruCache != null){
            lruCache.evictAll();
        }
    }

}
