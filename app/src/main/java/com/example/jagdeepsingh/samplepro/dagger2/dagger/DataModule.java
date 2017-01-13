package com.example.jagdeepsingh.samplepro.dagger2.dagger;

import android.content.Context;

import com.example.jagdeepsingh.samplepro.dagger2.database.DataService;
import com.example.jagdeepsingh.samplepro.dagger2.database.DbManager;
import com.example.jagdeepsingh.samplepro.dagger2.database.PrefManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Jagdeep.Singh on 11-01-2017.
 */

/**
 *  * @Module means this is module container
 * this container is looked upon on how to
 * instantiate any dependent object.
 * There can be many modules and
 * each module can define any number of
 * dependency objects as per need.
 */
@Module
public class DataModule {

    private Context context;
    private static final String PREF_NAME = "diPref";

    public DataModule(Context context){
        this.context = context;
    }

    /**
     * * @Provides means it is providing some objects
     * or it is stub where we define how to
     * instantiate dependency object.
     * Further both our DataService( Preference
     * or Database) needs context in
     * their constructor.
     * So you need to pass the context to
     * this class or provide a context in
     * any other way.
     * Meaning it won't automatically search for
     * context.
     * @return DataService
     */
    @Provides
    public DataService getDataService(){
//        return getPreferenceDataService();
        //          OR
            return getDatabaseDataService();
    }

    private DataService getPreferenceDataService(){
        return PrefManager.getInstance(context,PREF_NAME);
    }

    private DataService getDatabaseDataService(){
        return DbManager.getInstance(context);
    }
}
