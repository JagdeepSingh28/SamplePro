package com.example.jagdeepsingh.samplepro.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jagdeepsingh.samplepro.R;
import com.example.jagdeepsingh.samplepro.camera.service.MyCamera1Service;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicCameraActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    static int REQUEST_WRITE_EXTERNAL_STORAGE=1;
    private Button btn_capture;
    private ImageView iv_capture;
    private Bitmap bitmap;
    private Uri picUri;
    private String TAG = BasicCameraActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_camera);

        requestPermission(BasicCameraActivity.this);
        setIds();
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        btn_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                intent.addCategory(Intent.CATEGORY_OPENABLE);

                /**
                 * for Basic Camera application
                 */

                startService(new Intent(BasicCameraActivity.this, MyCamera1Service.class));

//                File file=getOutputMediaFile(1);
//                picUri = Uri.fromFile(file); // create
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,picUri);
//
//                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }

    private  File getOutputMediaFile(int type){

        File mediaStorageDir = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            //RUNTIME PERMISSION Android M
            if(PackageManager.PERMISSION_GRANTED==ActivityCompat.checkSelfPermission(BasicCameraActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "myPhoto");
            }else{
                requestPermission(BasicCameraActivity.this);
            }
        }

        /**Create the storage directory if it does not exist*/
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }

        /**Create a media file name*/
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == 1){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".png");
        } else {
            return null;
        }

        return mediaFile;
    }

    /**
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", picUri);
    }

    /*
     * Here we restore the fileUri again
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        picUri = savedInstanceState.getParcelable("file_uri");
    }

    private void setIds() {
        btn_capture = (Button) findViewById(R.id.btn_capture);
        iv_capture = (ImageView) findViewById(R.id.iv_capture);
    }

    private static void requestPermission(final Context context){
        if(ActivityCompat.shouldShowRequestPermissionRationale((Activity)context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // For example if the user has previously denied the permission.

            new AlertDialog.Builder(context)
                    .setMessage("permission_storage")
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    REQUEST_WRITE_EXTERNAL_STORAGE);
                        }
                    }).show();

        }else {
            // permission has not been granted yet. Request it directly.
            ActivityCompat.requestPermissions((Activity)context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(BasicCameraActivity.this,"permission_storage_success",
                            Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(BasicCameraActivity.this,"permission_storage_failure",
                            Toast.LENGTH_SHORT).show();
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
                return;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        InputStream inputStream = null;
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            try {
            if(bitmap != null){
                bitmap.recycle();
            }
//                inputStream = getContentResolver().openInputStream((Uri) data.getExtras().get("data"));
//                bitmap = BitmapFactory.decodeStream(inputStream);
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), picUri);
                int nh = (int) ( bitmap.getHeight() * (512.0 / bitmap.getWidth()) );
                Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 512, nh, true);
                iv_capture.setImageBitmap(scaled);
//                iv_capture.setImageURI(picUri);// setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
//                if(inputStream != null){
//                    try {
//                        inputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }
    }
}
