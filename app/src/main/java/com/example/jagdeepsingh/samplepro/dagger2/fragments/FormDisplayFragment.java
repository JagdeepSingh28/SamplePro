package com.example.jagdeepsingh.samplepro.dagger2.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jagdeepsingh.samplepro.R;

/**
 * Created by Jagdeep.Singh on 11-01-2017.
 */

public class FormDisplayFragment extends android.support.v4.app.Fragment{

    public FormDisplayFragment(){

    }

    public static FormDisplayFragment getInstance(Bundle bundle){
        FormDisplayFragment formDisplayFragment = new FormDisplayFragment();
        formDisplayFragment.setArguments(bundle);
        return formDisplayFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.form_display, container, false);
        return view;
    }
}
