package com.example.jagdeepsingh.samplepro.dagger2.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jagdeepsingh.samplepro.R;
import com.example.jagdeepsingh.samplepro.dagger2.fragments.FormDisplayFragment;
import com.example.jagdeepsingh.samplepro.dagger2.fragments.FormEditFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainDaggerActivity extends AppCompatActivity {

    // This is dependency Injection by ButterKnife
    @BindView(R.id.add_user)
    TextView add_user;
    @BindView(R.id.see_user)
    TextView see_user;

    private static final String FRAG_EDIT       = "FormEditFragmentPref",
                                FRAG_DISPLAY    = "FormDisplayFragmentPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dagger);
        ButterKnife.bind(this);

        // initially changing the fragment
        changeFragment(add_user);
    }

    private void changeFragment(View view) {
        switch (view.getId()) {
            case R.id.add_user:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, FormEditFragment.getInstance(null), FRAG_EDIT)
                        .commit();
                break;
            case R.id.see_user:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, FormDisplayFragment.getInstance(null), FRAG_DISPLAY)
                        .commit();
                break;
        }
    }

    @OnClick(R.id.add_user)
    public void addUser(View view){
        changeFragment(view);
        Toast.makeText(this, "add User Clicked", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.see_user)
    public void seeUser(View view){
        changeFragment(view);
        Toast.makeText(this, "See User Clicked", Toast.LENGTH_SHORT).show();
    }
}
