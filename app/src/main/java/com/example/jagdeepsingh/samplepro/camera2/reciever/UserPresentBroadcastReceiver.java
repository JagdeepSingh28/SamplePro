package com.example.jagdeepsingh.samplepro.camera2.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.jagdeepsingh.samplepro.camera2.Service.AndroidCameraApiWithoutSerfaceService;

/**
 * Created by Jagdeep.Singh on 23-11-2016.
 */
public class UserPresentBroadcastReceiver extends BroadcastReceiver {

    String TAG = UserPresentBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(Intent.ACTION_USER_PRESENT)){

            Log.e(TAG, "onReceive: ");

            Intent captureIntent = new Intent(context,AndroidCameraApiWithoutSerfaceService.class);
            context.startService(captureIntent);
            Toast.makeText(context, "User Unlocked the Phone", Toast.LENGTH_SHORT).show();

        }
    }
}
