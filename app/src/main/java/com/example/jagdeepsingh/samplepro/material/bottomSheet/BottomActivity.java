package com.example.jagdeepsingh.samplepro.material.bottomSheet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jagdeepsingh.samplepro.R;
import com.example.jagdeepsingh.samplepro.camera.service.SimpleCamera2ServicePublish;

/**
 * Created by jagdeep.singh on 22-08-2016.
 */
public class BottomActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = BottomActivity.class.getSimpleName();
    View bottom_sheet;
    BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_sheet_screen);

        bottom_sheet = findViewById(R.id.bottom_sheet);

        Button button1 = (Button) findViewById( R.id.button_1 );
        Button button2 = (Button) findViewById( R.id.button_2 );
        Button button3 = (Button) findViewById( R.id.button_3 );

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);


        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    bottomSheetBehavior.setPeekHeight(256);
                }
                Log.i(TAG, "onStateChanged: ");
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i(TAG, "onSlide: ");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch( v.getId() ) {
            case R.id.button_1: {
//                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                startService(new Intent(BottomActivity.this, SimpleCamera2ServicePublish.class));
                break;
            }
            case R.id.button_2: {
//                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                stopService(new Intent(BottomActivity.this, SimpleCamera2ServicePublish.class));
                break;
            }
            case R.id.button_3: {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            }
        }
    }
}
