package com.example.jagdeepsingh.samplepro.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jagdeep.singh on 09-02-2017.
 */

public class BaseActivity extends AppCompatActivity {

    FragmentTransaction fragmentTransaction;

    public void replaceFragment(int containerViewId, Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerViewId,fragment,fragment.getClass().getSimpleName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
