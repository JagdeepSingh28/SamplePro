package com.example.jagdeepsingh.samplepro.RxRetro.model;

import android.util.LruCache;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jagdeep.Singh on 17-11-2016.
 */

public class NetworkService {

//    private static String baseUrl ="https://dl.dropboxusercontent.com/u/57707756/";
    private static String baseUrl ="http://10.0.2.2:3000/";
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
//        @GET("FriendLocations.json")
        @GET("users")
        Call<FriendResponse> getFriends();

        @FormUrlEncoded
        @POST("users")
        Call<FriendResponse> getFriendsLogin(@FieldMap Map<String, String> userData);

        // Simple RxJava Call
//        @GET("FriendLocations.json")
        @POST("users")
        @Headers({ "Content-Type: application/json;charset=UTF-8"})
        Observable<FriendResponse> getFriendsObservable(@Body Map<String, String> userData);


    }

}
