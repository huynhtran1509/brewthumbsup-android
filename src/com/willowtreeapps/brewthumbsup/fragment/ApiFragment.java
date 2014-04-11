package com.willowtreeapps.brewthumbsup.fragment;

import com.google.inject.Inject;

import com.github.rtyley.android.sherlock.roboguice.fragment.RoboSherlockFragment;
import com.willowtreeapps.brewthumbsup.api.OpenBeerDatabaseApi;
import com.willowtreeapps.brewthumbsup.api.RottenTomatoesApi;
import com.willowtreeapps.brewthumbsup.api.model.Beer;
import com.willowtreeapps.brewthumbsup.api.model.Movie;
import com.willowtreeapps.brewthumbsup.api.response.BeersResponse;
import com.willowtreeapps.brewthumbsup.api.response.NewReleasesResponse;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * User: derek Date: 11/22/13 Time: 2:46 PM
 */
public class ApiFragment extends RoboSherlockFragment {

    public static interface Callback {

        void getMoviesAndBeerFinished(ArrayList<Movie> movies);
    }

    private Callback mCallback;
    private GetMoviesAndBeerTask mGetMoviesAndBeerTask;

    @Inject public RottenTomatoesApi mRottenTomatoesApi;
    @Inject public OpenBeerDatabaseApi mOpenBeerDatabaseApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        mGetMoviesAndBeerTask = new GetMoviesAndBeerTask();
        mGetMoviesAndBeerTask.execute();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (Callback) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    public class GetMoviesAndBeerTask extends AsyncTask<Void, Void, Void> {

        private ArrayList<Movie> mMovies = new ArrayList<Movie>();

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                // Get the list of movies
                NewReleasesResponse newReleasesResponse = mRottenTomatoesApi.getNewReleases();
                mMovies = newReleasesResponse.movies;

                // Get the list of beer with ABV 7
                BeersResponse beersResponse = mOpenBeerDatabaseApi.getBeers(7);
                ArrayList<Beer> beers = beersResponse.beers;

                // Add 7 random beers to each movie
                Random random = new Random();
                for (Movie m : mMovies) {
                    m.recommendedBeers = new ArrayList<Beer>();

                    for (int i = 0; i < 7; i++) {
                        int index = random.nextInt(beers.size());
                        m.recommendedBeers.add(beers.get(index));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            mCallback.getMoviesAndBeerFinished(mMovies);
        }
    }
}
