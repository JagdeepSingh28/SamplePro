package com.example.jagdeepsingh.samplepro.RxRetro.model;

import android.util.LruCache;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jagdeep.Singh on 17-11-2016.
 */

public class NetworkService {

    private static String baseUrl ="https://dl.dropboxusercontent.com/u/57707756/";
    public NetworkApi networkApi;
    private LruCache<Class<?>, Observable<?>> apiObservables;

    public NetworkService(){
        this(baseUrl);
    }

    public NetworkService(String baseUrl){

        apiObservables = new LruCache<>(10);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        networkApi = retrofit.create(NetworkApi.class);
    }


    /**
     * Method to either return a cached observable or prepare a new one.
     *
     * @param unPreparedObservable
     * @param clazz
     * @param cacheObservable
     * @param useCache
     * @return Observable ready to be subscribed to
     */
    public Observable<?> getPreparedObservable(Observable<?> unPreparedObservable, Class<?> clazz,
                                               boolean cacheObservable, boolean useCache){

        Observable<?> preparedObservable = null;

        if(useCache)//this way we don't reset anything in the cache if this is the only instance of us not wanting to use it.
            preparedObservable = apiObservables.get(clazz);

        if(preparedObservable!=null)
            return preparedObservable;

        //we are here because we have never created this observable before or we didn't want to use the cache...

        preparedObservable = unPreparedObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());

        if(cacheObservable){
            preparedObservable = preparedObservable.cache();
            apiObservables.put(clazz, preparedObservable);
        }

        return preparedObservable;
    }

    /**
     * Method to clear the entire cache of observables
     */
    public void clearCache(){
        apiObservables.evictAll();
    }


    public NetworkApi getAPI(){
        return networkApi;
    }

    public interface NetworkApi{

        // Simple Retrofit Call
        @GET("FriendLocations.json")
        Call<FriendResponse> getFriends();

        // Simple RxJava Call
        @GET("FriendLocations.json")
        Observable<FriendResponse> getFriendsObservable();


    }

}
