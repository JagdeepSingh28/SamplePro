package com.example.jagdeepsingh.samplepro.material.transition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jagdeepsingh.samplepro.R;

public class FirstTransitionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_transition);
    }

    public void transitionClick(View view){

        Intent intent = new Intent(this,SecondTransitionActivity.class);

        String transitionName = getResources().getString(R.string.simple_transition);

        View viewStart = findViewById(R.id.card_view);

        ActivityOptionsCompat activityOptionsCompat
                = ActivityOptionsCompat.makeSceneTransitionAnimation(this,viewStart,transitionName);

        ActivityCompat.startActivity(this,intent,activityOptionsCompat.toBundle());
    }
}
