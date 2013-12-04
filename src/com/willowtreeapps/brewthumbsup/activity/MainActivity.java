package com.willowtreeapps.brewthumbsup.activity;

import com.google.gson.Gson;

import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.willowtreeapps.brewthumbsup.R;
import com.willowtreeapps.brewthumbsup.adapter.MovieAdapter;
import com.willowtreeapps.brewthumbsup.api.model.Movie;
import com.willowtreeapps.brewthumbsup.fragment.ApiFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import roboguice.inject.InjectView;

public class MainActivity extends RoboSherlockFragmentActivity implements ApiFragment.Callback {

    @InjectView(R.id.progress) private ProgressBar progress;
    @InjectView(R.id.list_view) private ListView listView;

    private ApiFragment mApiFragment;
    private Gson mGson = new Gson();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        mApiFragment = (ApiFragment) fm.findFragmentByTag("api");

        // If the Fragment is non-null, then it is currently being
        // retained across a configuration change.
        if (mApiFragment == null) {
            mApiFragment = new ApiFragment();
            fm.beginTransaction().add(mApiFragment, "api").commit();
        }

        listView.setAdapter(new MovieAdapter(this));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie m = (Movie) adapterView.getAdapter().getItem(i);
                startActivity(RecommendedBeersActivity.getIntent(MainActivity.this, mGson, m));
            }
        });

        progress.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    @Override
    public void getMoviesAndBeerFinished(ArrayList<Movie> movies) {
        ((MovieAdapter) listView.getAdapter()).setData(movies);
        ((MovieAdapter) listView.getAdapter()).notifyDataSetChanged();

        progress.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }
}

