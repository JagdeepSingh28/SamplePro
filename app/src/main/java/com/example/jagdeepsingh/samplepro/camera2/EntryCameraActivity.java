package com.example.jagdeepsingh.samplepro.camera2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.jagdeepsingh.samplepro.R;
import com.example.jagdeepsingh.samplepro.camera2.Service.AndroidCameraApiWithoutSerfaceService;

public class EntryCameraActivity extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_camera);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(EntryCameraActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CAMERA_PERMISSION);
            return;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                // close the app
                Toast.makeText(EntryCameraActivity.this, "Sorry!!!, you can't use this app without granting permission", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    public void cameraClick(View view) {

//        if(Build.VERSION.SDK_INT >= 23) {
//            if (!Settings.canDrawOverlays(EntryCameraActivity.this)) {
//                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                        Uri.parse("package:" + getPackageName()));
//                startActivityForResult(intent, 1234);
//            }
//        }
//        else
//        {
//            Intent front_translucent = new Intent(this, MyCameraService.class);
//            front_translucent.putExtra("Front_Request", true);
////            front_translucent.putExtra("Quality_Mode",
////                    camCapture.getQuality());
//            startService(
//                    front_translucent);
//        }


            Intent captureIntent = new Intent(this,AndroidCameraApiWithoutSerfaceService.class);
//            captureIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//          captureIntent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//          context.startActivity(captureIntent);
            startService(captureIntent);
    }
}
