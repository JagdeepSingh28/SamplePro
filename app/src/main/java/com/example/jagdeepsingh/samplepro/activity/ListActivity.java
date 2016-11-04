package com.example.jagdeepsingh.samplepro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jagdeepsingh.samplepro.R;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = new Intent(ListActivity.this,DetailActivity.class);

        startActivity(intent);

    }
}
