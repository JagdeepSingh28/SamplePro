package com.example.jagdeepsingh.samplepro.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jagdeepsingh.samplepro.R;

import butterknife.BindView;

public class ListingFragment extends Fragment {

    interface OnListingClickListener{
        void openDetailFragment();
    }
    View view;
    OnListingClickListener listingClickListener;

    @BindView(R.id.listing_rv)
    RecyclerView listing_rv;

    public ListingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_listing, container, false);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listingClickListener = (OnListingClickListener) activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        openDetailFragment();
    }

    private void openDetailFragment() {
        listingClickListener.openDetailFragment();
//        ((MainFragmentContainerActivity)getActivity()).openDetailFragment();
    }
}
