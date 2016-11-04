package com.example.jagdeepsingh.samplepro.headless;

/**
 * Created by Jagdeep.Singh on 04-11-2016.
 */

public interface TaskListener {

    void onTaskStarted();

    void onTaskProgressUpdate(int progress);

    void onTaskFinished(String result);
}
