package com.example.jagdeepsingh.samplepro.material.faceDetection;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jagdeepsingh.samplepro.R;
import com.example.jagdeepsingh.samplepro.camera.AndroidCameraApi2;
import com.example.jagdeepsingh.samplepro.material.faceDetection.view.FaceOverLayView;

import java.io.InputStream;

public class FaceDetectionActivity extends AppCompatActivity {

    FaceOverLayView face_detect_id;
    private InputStream inputStream;
    private Bitmap bitmap;
    int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_detection);

        face_detect_id = (FaceOverLayView) findViewById(R.id.face_detect_id);

        /**
         * getting image from raw folder
         */
//        inputStream = getResources().openRawResource(R.raw.face);
//        bitmap = BitmapFactory.decodeStream(inputStream);
//        face_detect_id.setBitmap(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap mbitmap = BitmapFactory.decodeFile(data.getStringExtra("file"),options);
            bitmap = Bitmap.createScaledBitmap(mbitmap, 960, 720, false);
            face_detect_id.setBitmap(bitmap);
        }
    }

    public void TakePic(View view){
        Intent intent = new Intent(FaceDetectionActivity.this, AndroidCameraApi2.class);
        startActivityForResult(intent,REQUEST_CODE);
    }

    public void DetectFace(View view){
        if(bitmap != null)
        face_detect_id.setBitmap(bitmap);
    }

}
