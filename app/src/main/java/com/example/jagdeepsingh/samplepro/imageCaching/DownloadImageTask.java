package com.example.jagdeepsingh.samplepro.imageCaching;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by jagdeep.singh on 05-10-2016.
 */

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    private static final String TAG = DownloadImageTask.class.getSimpleName();
    private final ImageView imageView;
    private int             inSampleSize = 1;
    private String          imageURL;
    private int             desiredWidth;
    private int             desiredHeight;
    private ImageCache      imageCache;
    private Bitmap          downloadedBitmap = null;

    public DownloadImageTask(ImageView imageView, int desiredWidth, int desiredHeight){
        this.imageView      =   imageView;
        this.desiredWidth   =   desiredWidth;
        this.desiredHeight  =   desiredHeight;
        imageCache = ImageCache.getInstance();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        imageURL = params[0];
        return getImageFromServer(imageURL);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        Log.i(TAG, "onPostExecute: Download Complete for image " + imageURL);
        // Add bitmap to the cache
        if(bitmap != null){
            imageView.setImageBitmap(bitmap);
        }
    }

    public Bitmap getImageFromServer(String imageURL) {
        if(imageCache.getImageFromCahce(imageURL) == null){
            InputStream inputStream = null;
            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inJustDecodeBounds = true;  -- > Image is returning null due to this
            options.inSampleSize = inSampleSize;

            try {
                URL url = new URL(imageURL);
                URLConnection urlConnection = url.openConnection();
                HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    inputStream = httpURLConnection.getInputStream();
                    downloadedBitmap = BitmapFactory.decodeStream(inputStream, null, options);
                    inputStream.close();
                }
                imageCache.addImageToCache(imageURL,downloadedBitmap);
                return downloadedBitmap;
            }catch (IOException e){
                Log.i(TAG, "getImageFromServer: exception is" + e);
            }
        }else{
            return imageCache.getImageFromCahce(imageURL);
        }
        return null;
    }
}
