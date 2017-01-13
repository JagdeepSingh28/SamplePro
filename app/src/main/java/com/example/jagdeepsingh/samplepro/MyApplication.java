package com.example.jagdeepsingh.samplepro;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.example.jagdeepsingh.samplepro.InternetObserver.NetworkObservable;
import com.example.jagdeepsingh.samplepro.RxRetro.model.NetworkService;
import com.example.jagdeepsingh.samplepro.dagger2.dagger.DaggerDataComponent;
import com.example.jagdeepsingh.samplepro.dagger2.dagger.DataComponent;
import com.example.jagdeepsingh.samplepro.dagger2.dagger.DataModule;
import com.example.jagdeepsingh.samplepro.imageCaching.ImageCache;

/**
 * Created by jagdeep.singh on 06-10-2016.
 */

public class MyApplication extends MultiDexApplication {

    ImageCache imageCache;
    private NetworkService networkService;
    private DataComponent dataComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        networkService = new NetworkService();

        // Creating one time instance of ImageCache
        imageCache = ImageCache.getInstance();
        imageCache.initializeCache();

        /**
         * * This part may be confusing
         * at first.
         * If you at first simply write this
         * line, the IDE would throw error as
         * these classes won't be built until
         * and unless you go to Build->RebuildProject.
         * Once you do that, go to
         * app/build/generated/source/apt/...
         * You will see these generated class
         * and it won't throw any error on IDE.
         * DataComponent= name of our component.
         * Dagger by default create the component as
         * DaggerDataComponent
         * dataModule() method simply means your are
         * trying to use DataModule class as defined
         * in the @Component(modules = { DataModule.class }).
         * So if there is another module named HelloModule
         * and being used by the DataComponent, there
         * will be method named
         * helloModule().
         */
        dataComponent = DaggerDataComponent.builder().
                dataModule(new DataModule(this)).build();
        /**
         * * @Component(modules = { DataModule.class }),
         * simply creates a
         * setter in DaggerDataComponent class as
         * dataModule(DataModule dataModule). Look
         * upon the generated class. If you don't set
         * the DataModule, it will be null i.e. if you won't
         * do as above statement, DataComponent will be created
         * but with null dataModule. So annotating and
         * setting the module is both need for component
         * to function properly
         */
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public NetworkService getNetworkService(){
        return networkService;
    }

    public NetworkObservable getNetworkObservable(){
        return NetworkObservable.getInstance();
    }

    public DataComponent getDataComponent() {
        return dataComponent;
    }
}
