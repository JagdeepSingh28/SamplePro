package com.example.jagdeepsingh.samplepro.services;

import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by jagdeep.singh on 08-11-2016.
 */

public class AdditionService {

    public IBinder onBind(Intent intent){
        return mBinder;
    }

    private SimpleAddAidlInterface.Stub mBinder = new SimpleAddAidlInterface.Stub() {
        @Override
        public int add(int num1, int num2) throws RemoteException {
            return(num1+num2);
        }
    };
}
