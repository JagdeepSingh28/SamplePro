package com.example.jagdeepsingh.samplepro.InternetObserver;

import java.util.Observable;

/**
 * Created by Jagdeep.Singh on 03-01-2017.
 */

public class NetworkObservable extends Observable {
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
        synchronized (this) {
            setChanged();
            notifyObservers(online);
        }
    }
}
