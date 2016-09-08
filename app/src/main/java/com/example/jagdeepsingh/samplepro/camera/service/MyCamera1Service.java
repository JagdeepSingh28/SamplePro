package com.example.jagdeepsingh.samplepro.camera.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Environment;
import android.os.IBinder;
import android.os.SystemClock;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyCamera1Service extends Service {
    public MyCamera1Service() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        takePhoto();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void takePhoto() {

        System.out.println("Preparing to take photo");
        Camera camera = null;

        int cameraCount = 0;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();
        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            SystemClock.sleep(1000);

            Camera.getCameraInfo(camIdx, cameraInfo);

            try {
                camera = Camera.open(camIdx);
            } catch (RuntimeException e) {
                System.out.println("Camera not available: " + camIdx);
                camera = null;
                //e.printStackTrace();
            }
            try {
                if (null == camera) {
                    System.out.println("Could not get camera instance");
                } else {
                    System.out.println("Got the camera, creating the dummy surface texture");
                    //SurfaceTexture dummySurfaceTextureF = new SurfaceTexture(0);
                    try {
                        //camera.setPreviewTexture(dummySurfaceTextureF);
                        camera.setPreviewTexture(new SurfaceTexture(0));
                        camera.startPreview();
                    } catch (Exception e) {
                        System.out.println("Could not set the surface preview texture");
                        e.printStackTrace();
                    }
                    camera.takePicture(null, null, new Camera.PictureCallback() {

                        @Override
                        public void onPictureTaken(byte[] data, Camera camera) {
                            File pictureFileDir = new File(Environment.getExternalStorageDirectory()+"/pic.jpg");
                            if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {
                                return;
                            }
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
                            String date = dateFormat.format(new Date());
                            String photoFile = "PictureFront_" + "_" + date + ".jpg";
                            String filename = pictureFileDir.getPath() + File.separator + photoFile;
                            File mainPicture = new File(filename);
//                            addImageFile(mainPicture);

                            try {
                                FileOutputStream fos = new FileOutputStream(pictureFileDir);
                                fos.write(data);
                                fos.close();
                                System.out.println("image saved");
                            } catch (Exception error) {
                                System.out.println("Image could not be saved");
                            }
                            camera.release();
                        }
                    });
                }
            } catch (Exception e) {
                camera.release();
            }


        }
    }
}
