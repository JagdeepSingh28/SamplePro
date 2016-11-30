package com.example.jagdeepsingh.samplepro.camera2.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.jagdeepsingh.samplepro.camera2.AndroidCameraApi;

/**
 * Created by Jagdeep.Singh on 23-11-2016.
 */
public class UserPresentBroadcastReceiver extends BroadcastReceiver {

    String TAG = UserPresentBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            Log.d(TAG, "onReceive: ");
            Intent captureIntent = new Intent(context,AndroidCameraApi.class);
            captureIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            captureIntent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(captureIntent);
//            Toast.makeText(context, "User Unlocked the Phone", Toast.LENGTH_SHORT).show();
        }
    }
}
