package com.example.jagdeepsingh.samplepro.recycler;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jagdeepsingh.samplepro.R;

import java.util.ArrayList;

/**
 * Created by Jagdeep.Singh on 02-08-2016.
 */
public class MyRecyclerActivity extends Activity {

    RecyclerView recycler_view;
    MyRecyclerAdapter myRecyclerAdapter;
    MyItemdecorator myItemdecorator;
    ArrayList<String> stringArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_recycler_screen);

        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        populateDummyData();
        myRecyclerAdapter   = new MyRecyclerAdapter();
        myItemdecorator     = new MyItemdecorator(MyRecyclerActivity.this);

        myRecyclerAdapter.setData(stringArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyRecyclerActivity.this);
        recycler_view.setHasFixedSize(true);

        recycler_view.addItemDecoration(myItemdecorator);
        recycler_view.setLayoutManager(linearLayoutManager);

        recycler_view.setAdapter(myRecyclerAdapter);
    }

    private void populateDummyData() {

        for (int i = 0; i < 100; i++) {
            stringArrayList.add(" The Text is " + i );
        }
    }
}
