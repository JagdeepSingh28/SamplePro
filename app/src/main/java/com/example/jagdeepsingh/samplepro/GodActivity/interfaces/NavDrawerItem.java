package com.example.jagdeepsingh.samplepro.GodActivity.interfaces;

/**
 * Created by Jagdeep.Singh on 22-11-2016.
 */

public interface NavDrawerItem {

    int getId();
    String getLabel();
    int getType();
    boolean isEnabled();
    boolean updateActionBarTitle();
}
