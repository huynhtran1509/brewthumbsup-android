package com.willowtreeapps.brewthumbsup.activity;

import com.google.gson.Gson;

import com.actionbarsherlock.view.MenuItem;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.viewpagerindicator.CirclePageIndicator;
import com.willowtreeapps.brewthumbsup.R;
import com.willowtreeapps.brewthumbsup.adapter.BeerAdapter;
import com.willowtreeapps.brewthumbsup.api.model.Movie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import roboguice.inject.InjectView;

public class RecommendedBeersActivity extends RoboSherlockFragmentActivity {

    private static final String MOVIE_JSON_KEY = "RecommendedBeersActivity_MovieJsonKey";

    @InjectView(R.id.pager) private ViewPager pager;
    @InjectView(R.id.indicator) private CirclePageIndicator indicator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_beers);

        Intent myIntent = getIntent();
        Movie movie = new Gson().fromJson(myIntent.getStringExtra(MOVIE_JSON_KEY), Movie.class);

        getSupportActionBar().setTitle("Recommended Beers for " + movie.title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pager.setAdapter(new BeerAdapter(getSupportFragmentManager(), movie.recommendedBeers));
        indicator.setViewPager(pager);
    }

    public static Intent getIntent(Context context, Gson gson, Movie movie) {
        Intent intent = new Intent(context, RecommendedBeersActivity.class);
        intent.putExtra(MOVIE_JSON_KEY, gson.toJson(movie));
        return intent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}