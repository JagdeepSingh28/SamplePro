package com.example.jagdeepsingh.samplepro.material.constraint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.jagdeepsingh.samplepro.R;

public class ConstraintActivity extends AppCompatActivity {

    private static final String TAG = ConstraintActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint);
        Log.i(TAG, "onCreate: ");
    }
}
