package com.example.jagdeepsingh.samplepro.RxRetro.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jagdeepsingh.samplepro.MyApplication;
import com.example.jagdeepsingh.samplepro.R;
import com.example.jagdeepsingh.samplepro.RxRetro.model.FriendResponse;
import com.example.jagdeepsingh.samplepro.RxRetro.model.NetworkService;
import com.example.jagdeepsingh.samplepro.RxRetro.presenter.PresenterLayer;

import retrofit2.Response;

public class ActivityView extends AppCompatActivity implements View.OnClickListener {

    private static final String EXTRA_RX = "extra_rx";
    private Button rxCall, retroCall;
    private TextView rxResponse, retroResponse;
    private ProgressBar progressBar;
    private PresenterLayer presenter;
    private NetworkService networkService;
    private boolean rxCallInWorks = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        setUI();

        if(savedInstanceState!=null){
            rxCallInWorks = savedInstanceState.getBoolean(EXTRA_RX);
        }
    }

    private void setUI() {
        rxCall = (Button)findViewById(R.id.rxCall);
        retroCall = (Button)findViewById(R.id.retroCall);
        rxResponse = (TextView)findViewById(R.id.rxResponse);
        retroResponse = (TextView)findViewById(R.id.retroResponse);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        rxCall.setOnClickListener(this);
        retroCall.setOnClickListener(this);

        networkService = ((MyApplication)getApplication()).getNetworkService();
        presenter = new PresenterLayer(this,networkService);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.rxUnSubscribe();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(rxCallInWorks)
            presenter.loadRxData();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(EXTRA_RX,rxCallInWorks);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.retroCall:
                // This will not presist data on Configuration change
                presenter.loadRetroData();
                break;
            case R.id.rxCall:
                // This will presist data on Configuration change
                rxCallInWorks = true;
                presenter.loadRxData();
                break;
        }
    }

    public void showRetroInProcess() {
        retroResponse.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        retroCall.setEnabled(false);
        rxCall.setEnabled(false);
    }

    public void showRetroResult(Response<FriendResponse> response) {
        retroResponse.setText(response.body().friendLocations.data.friend.get(0).friendName);
        retroResponse.setVisibility(View.VISIBLE);
        retroCall.setEnabled(true);
        rxCall.setEnabled(true);
        progressBar.setVisibility(View.GONE);
    }

    public void showRetroError(Throwable t) {
        Log.d("TAG", t.toString());
        retroResponse.setText("ERROR");
        retroResponse.setVisibility(View.VISIBLE);
        retroCall.setEnabled(true);
        rxCall.setEnabled(true);
        progressBar.setVisibility(View.GONE);
    }

    public void showRxInProcess() {
        rxResponse.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        retroCall.setEnabled(false);
        rxCall.setEnabled(false);
    }

    public void showRxResults(FriendResponse friendResponse) {
        rxResponse.setText(friendResponse.friendLocations.data.friend.get(0).friendName);
        rxResponse.setVisibility(View.VISIBLE);
        rxCall.setEnabled(true);
        retroCall.setEnabled(true);
        progressBar.setVisibility(View.GONE);
    }

    public void showRxFailure(Throwable e) {
        Log.d("TAG", e.toString());
        rxResponse.setText("ERROR");
        rxResponse.setVisibility(View.VISIBLE);
        rxCall.setEnabled(true);
        retroCall.setEnabled(true);
        progressBar.setVisibility(View.GONE);
    }
}
