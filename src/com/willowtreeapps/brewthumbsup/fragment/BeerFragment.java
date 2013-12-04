package com.willowtreeapps.brewthumbsup.fragment;

import com.google.gson.Gson;

import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.willowtreeapps.brewthumbsup.R;
import com.willowtreeapps.brewthumbsup.api.model.Beer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import roboguice.inject.InjectView;

/**
 * User: derek Date: 12/4/13 Time: 9:48 AM
 */
public class BeerFragment extends RoboSherlockFragment {

    private static final String BEER_JSON_KEY = "BeerFragment_BeerJsonKey";

    @InjectView(R.id.abv) TextView abv;
    @InjectView(R.id.brewery) TextView brewery;
    @InjectView(R.id.name) TextView name;
    @InjectView(R.id.description) TextView description;
    @InjectView(R.id.url) ImageView url;

    private Beer mBeer;
    private Gson mGson = new Gson();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        parseArguments(args);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        abv.setText(mBeer.abv + "%");
        brewery.setText(mBeer.brewery.name);
        name.setText(mBeer.name);
        description.setText(mBeer.description);
        if (!TextUtils.isEmpty(mBeer.brewery.url)) {
            url.setVisibility(View.VISIBLE);
            url.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(mBeer.brewery.url));
                    startActivity(intent);
                }
            });
        }
    }

    private void parseArguments(Bundle args) {
        if (args != null && args.containsKey(BEER_JSON_KEY)) {
            mBeer = mGson.fromJson(args.getString(BEER_JSON_KEY), Beer.class);
        }
    }

    public static Bundle getArgs(Gson gson, Beer beer) {
        Bundle args = new Bundle();
        args.putString(BEER_JSON_KEY, gson.toJson(beer));
        return args;
    }
}
