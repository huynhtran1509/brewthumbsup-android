package com.willowtreeapps.brewthumbsup.activity;

import com.google.gson.Gson;

import com.actionbarsherlock.view.MenuItem;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockFragmentActivity;
import com.willowtreeapps.brewthumbsup.R;
import com.willowtreeapps.brewthumbsup.adapter.BeerAdapter;
import com.willowtreeapps.brewthumbsup.api.model.Movie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import roboguice.inject.InjectView;

public class RecommendedBeersActivity extends RoboSherlockFragmentActivity {

    private static final String MOVIE_JSON_KEY = "RecommendedBeersActivity_MovieJsonKey";

    @InjectView(R.id.list_view) private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommended_beers_activity);

        Intent myIntent = getIntent();
        Movie movie = new Gson().fromJson(myIntent.getStringExtra(MOVIE_JSON_KEY), Movie.class);

        getSupportActionBar().setTitle("Recommended Beers for " + movie.title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView.setAdapter(new BeerAdapter(this, movie.recommendedBeers));
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

