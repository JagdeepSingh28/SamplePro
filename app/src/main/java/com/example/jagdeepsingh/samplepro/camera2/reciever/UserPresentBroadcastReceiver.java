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

            Log.d(TAG, "onReceive: ");

//            Intent front_translucent = new Intent(context, MyCameraService.class);
//            front_translucent.putExtra("Front_Request", true);
////            front_translucent.putExtra("Quality_Mode",
////                    camCapture.getQuality());
//            context.startService(
//                    front_translucent);





            Intent captureIntent = new Intent(context,AndroidCameraApiWithoutSerfaceService.class);
//            captureIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            captureIntent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//            context.startActivity(captureIntent);
            context.startService(captureIntent);
            Toast.makeText(context, "User Unlocked the Phone", Toast.LENGTH_SHORT).show();

        }
    }
}
