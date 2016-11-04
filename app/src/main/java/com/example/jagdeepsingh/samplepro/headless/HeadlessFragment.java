package com.example.jagdeepsingh.samplepro.headless;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HeadlessFragment extends Fragment {


    public static final String TAG = HeadlessFragment.class.getSimpleName();
    TaskListener taskListener;
    MyAsyncTask myAsyncTask;

    public HeadlessFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        taskListener = (TaskListener)context;
    }

    public void startExecutingTask(){
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }

    public void cancelExecutingTask(){
        myAsyncTask.cancel(true);
    }

    private class MyAsyncTask extends AsyncTask<Void,Integer,Void> {

//        TaskListener taskListener;

//        MyAsyncTask(TaskListener taskListener){

//            taskListener = taskListener;
//        }

        @Override
        protected void onPreExecute() {
            if(taskListener!=null)
            taskListener.onTaskStarted();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(1000);
                    publishProgress(i*10);
                }
            }catch(InterruptedException e){
                Log.i(TAG, "doInBackground: ");
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(taskListener!=null)
            taskListener.onTaskProgressUpdate(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(taskListener!=null)
            taskListener.onTaskFinished("Task Finished");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        taskListener = null;
    }
}
