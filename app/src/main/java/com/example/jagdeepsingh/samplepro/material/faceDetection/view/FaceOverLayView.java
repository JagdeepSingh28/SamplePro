package com.example.jagdeepsingh.samplepro.material.faceDetection.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

/**
 * Created by jagdeep.singh on 08-09-2016.
 */
public class FaceOverLayView extends View {
    private static final String TAG = FaceOverLayView.class.getSimpleName();
    private Bitmap mBitmap;
    private SparseArray<Face> faceSparseArray;

    public FaceOverLayView(Context context) {
        super(context);
    }

    public FaceOverLayView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FaceOverLayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FaceOverLayView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setBitmap(Bitmap bitmap){
        mBitmap = bitmap;

        FaceDetector faceDetector = new FaceDetector.Builder(getContext())
                .setTrackingEnabled(true)
                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                .setMode(FaceDetector.ACCURATE_MODE)
                .build();

        if(!faceDetector.isOperational()){
            Toast.makeText(getContext(), " Face Detector not loaded ", Toast.LENGTH_SHORT).show();
        }else{
            Frame frame = new Frame.Builder().setBitmap(mBitmap).build();
            faceSparseArray = faceDetector.detect(frame);
            faceDetector.release();
        }

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mBitmap!=null && faceSparseArray != null){
            double scale = drawBitmap(canvas);
            drawRectangleOnBitmap(canvas,scale);
        }
    }

    private double drawBitmap(Canvas canvas) {
        double viewWidth = canvas.getWidth();
        double viewHeight = canvas.getHeight();
        double imageWidth = mBitmap.getWidth();
        double imageHeight = mBitmap.getHeight();
        Log.i(TAG, "drawBitmap: vW - "+viewHeight+"vH - "+viewHeight+"iW - "+imageWidth+"iH -"+imageHeight);
        double scale = Math.min(viewWidth / imageWidth, viewHeight / imageHeight);

        Rect destBounds = new Rect(0, 0, (int)(imageWidth * scale), (int)(imageHeight * scale));
        canvas.drawBitmap(mBitmap, null, destBounds, null);
        return scale;
    }

    private void drawRectangleOnBitmap(Canvas canvas, double scale) {
        //This should be defined as a member variable rather than
        //being created on each onDraw request, but left here for
        //emphasis.
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        float left = 0;
        float top = 0;
        float right = 0;
        float bottom = 0;

        for( int i = 0; i < faceSparseArray.size(); i++ ) {
            Face face = faceSparseArray.valueAt(i);

            left = (float) ( face.getPosition().x * scale );
            top = (float) ( face.getPosition().y * scale );
            right = (float) scale * ( face.getPosition().x + face.getWidth() );
            bottom = (float) scale * ( face.getPosition().y + face.getHeight() );

            canvas.drawRect( left, top, right, bottom, paint );
        }
    }
}
