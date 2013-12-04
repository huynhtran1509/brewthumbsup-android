package com.willowtreeapps.brewthumbsup.adapter;

import com.google.gson.Gson;

import com.willowtreeapps.brewthumbsup.api.model.Beer;
import com.willowtreeapps.brewthumbsup.fragment.BeerFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * User: derek Date: 1/21/13 Time: 4:08 PM
 */
public class BeerAdapter extends FragmentPagerAdapter {

    private ArrayList<Beer> mBeers;
    private Gson mGson;

    public BeerAdapter(FragmentManager fm, ArrayList<Beer> beers) {
        super(fm);
        mGson = new Gson();
        mBeers = beers;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new BeerFragment();
        fragment.setArguments(BeerFragment.getArgs(mGson, mBeers.get(position)));
        return fragment;
    }

    @Override
    public int getCount() {
        return (mBeers == null ? 0 : mBeers.size());
    }
}
