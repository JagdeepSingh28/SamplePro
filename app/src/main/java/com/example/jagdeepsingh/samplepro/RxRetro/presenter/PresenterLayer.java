package com.example.jagdeepsingh.samplepro.RxRetro.presenter;

import com.example.jagdeepsingh.samplepro.RxRetro.model.FriendResponse;
import com.example.jagdeepsingh.samplepro.RxRetro.model.NetworkService;
import com.example.jagdeepsingh.samplepro.RxRetro.view.ActivityView;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jagdeep.Singh on 17-11-2016.
 */

public class PresenterLayer implements PresenterInteractor {

    private ActivityView view;
    private NetworkService service;
    private Subscription subscription;


    public PresenterLayer(ActivityView view, NetworkService service){
        this.view = view;
        this.service = service;
    }

    @Override
    public void loadRxData() {
        view.showRxInProcess();
        HashMap<String,String> params = new HashMap<>();
        params.put("userEmail","JagdeepRx");
        params.put("userPassword","JagdeepRx");
        /*
        Without cached case
         */
//      Observable<FriendResponse> observableCall = service.getAPI().getFriendsObservable();
        /*
        Cached case
         */
        Observable<FriendResponse> observableCall = (Observable<FriendResponse>)
                service.getPreparedObservable(service.getAPI().getFriendsObservable(params), FriendResponse.class, true, true);
        subscription = observableCall.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FriendResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showRxFailure(e);
                    }

                    @Override
                    public void onNext(FriendResponse friendResponse) {
                        view.showRxResults(friendResponse);
                    }
                });
    }

    @Override
    public void loadRetroData() {
        view.showRetroInProcess();
        HashMap<String,String> params = new HashMap<>();
        params.put("userEmail","Jagdeep1");
        params.put("userPassword","Jagdeep1");

        Call<FriendResponse> call = service.getAPI().getFriendsLogin(params);
        call.enqueue(new Callback<FriendResponse>() {
            @Override
            public void onResponse(Call<FriendResponse> call, Response<FriendResponse> response) {
                view.showRetroResult(response);
            }

            @Override
            public void onFailure(Call<FriendResponse> call, Throwable t) {
                view.showRetroError(t);
            }
        });
    }

    @Override
    public void rxUnSubscribe() {
        if(subscription!=null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
