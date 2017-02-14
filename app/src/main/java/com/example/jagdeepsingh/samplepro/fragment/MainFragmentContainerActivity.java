package com.example.jagdeepsingh.samplepro.fragment;


import android.os.Bundle;

import com.example.jagdeepsingh.samplepro.R;

import butterknife.ButterKnife;

public class MainFragmentContainerActivity extends BaseActivity implements ListingFragment.OnListingClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment_container);
        openListingFragment();
        ButterKnife.bind(this);
    }

    private void openListingFragment() {
        replaceFragment(R.id.listing_frag_frame,new ListingFragment());
    }

    @Override
    public void openDetailFragment() {
        replaceFragment(R.id.detail_frag_frame,new DetailFragment());
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()>0){
            getSupportFragmentManager().popBackStack();
        }else
            super.onBackPressed();
    }
}
