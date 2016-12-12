package com.example.jagdeepsingh.samplepro.InternetObserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.Observable;

/**
 * Created by jagdeep.singh on 12-12-2016.
 */

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("NetworkChangeReceiver","Connection status changed");

        getObservable().connectionChanged(isOnline(context));
    }

    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());
    }

    public static class NetworkObservable extends Observable{
        //create singleton of NetworkObservable

        public static NetworkObservable networkObservable;

        private NetworkObservable(){

        }

        public static NetworkObservable getInstance(){
            if(networkObservable!=null){
                return networkObservable;
            }else
                return new NetworkObservable();
        }

        public void connectionChanged(boolean online){
            setChanged();
            notifyObservers(online);
        }
    }

    public static NetworkObservable getObservable(){
        return NetworkObservable.getInstance();
    }
}
