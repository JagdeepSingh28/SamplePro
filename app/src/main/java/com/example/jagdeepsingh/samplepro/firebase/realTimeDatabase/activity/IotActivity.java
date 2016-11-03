package com.example.jagdeepsingh.samplepro.firebase.realTimeDatabase.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.jagdeepsingh.samplepro.R;
import com.example.jagdeepsingh.samplepro.firebase.Config;
import com.example.jagdeepsingh.samplepro.firebase.realTimeDatabase.model.Person;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class IotActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = IotActivity.class.getSimpleName();
    ToggleButton toggle_iot_btn;
    TextView led_status_tv;
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iot);

        initializeIds();
        setClickListener();
    }

    private void setClickListener() {

    }

    private void initializeIds() {
        toggle_iot_btn          = (ToggleButton) findViewById(R.id.toggle_iot_btn);
        led_status_tv           = (TextView) findViewById(R.id.led_status_tv);
        toggle_iot_btn.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton toggleButton, boolean isChecked) {
                if(isChecked){
                    firebase.setValue("down");
                }else{
                    firebase.setValue("up");
                }
            }});
        /**
         * FireBase Initialization
         */
        Firebase.setAndroidContext(this);
        firebase        = new Firebase(Config.FIREBASE_URL);


        /**
         * Getting data from fireBase Real Time Database
         */
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i(TAG, "onDataChange: "+ dataSnapshot.getValue());
                String ledStatus = dataSnapshot.getValue()+"";
                if(ledStatus.equalsIgnoreCase("down")){
//                    toggle_iot_btn.setChecked(true);
                    led_status_tv.setText("LED IS ON");
                }
                else{
//                    toggle_iot_btn.setChecked(false);
                    led_status_tv.setText("LED IS OFF");
                }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(IotActivity.this, "Failed To retrieve Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toggle_iot_btn:
                if(!toggle_iot_btn.isChecked()){
                    firebase.setValue("down");
                    toggle_iot_btn.setChecked(true);
                }else{
                    firebase.setValue("up");
                    toggle_iot_btn.setChecked(false);
                }
        }
    }
}
