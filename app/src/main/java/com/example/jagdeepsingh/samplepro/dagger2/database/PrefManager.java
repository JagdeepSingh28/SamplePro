package com.example.jagdeepsingh.samplepro.dagger2.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.jagdeepsingh.samplepro.dagger2.model.User;
import com.google.gson.Gson;

/**
 * Created by Jagdeep.Singh on 11-01-2017.
 */

public class PrefManager implements DataService {

    private static final String TAG = PrefManager.class.getSimpleName();
    private SharedPreferences sharedPreferences;
    private static PrefManager prefManager;
    public static String PREF_NAME = "diPref";
    private Context context;

    private PrefManager(Context context, String prefName){
        SharedPreferences sharedPreferences = context.getSharedPreferences(prefName,Context.MODE_PRIVATE);
        this.context = context;
    }

    /**
     * Creating Singlton here
     * @param context
     * @param prefName
     * @return
     */
    public static PrefManager getInstance(Context context, String prefName){
        if(prefManager == null)
            prefManager = new PrefManager(context,prefName);
        return prefManager;
    }

    @Override
    public User getUser() {
        User user = null;
        String jsonString = sharedPreferences.getString(String.valueOf(User.getUniqueUserId()),null);
        if(jsonString!= null){
            user = new Gson().fromJson(jsonString,User.class);
        }
        return user;
    }

    @Override
    public void storeUser(User user) {
        if(user == null) return;
        String userJsonString = new Gson().toJson(user);
        Log.i(TAG, "storeUser: ");
        saveUser(user,userJsonString);
    }

    private void saveUser(User user, String userJsonString) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString((user.getUserId()+""), userJsonString);
        editor.apply();
    }
}
