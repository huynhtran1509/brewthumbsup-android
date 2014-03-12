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
import java.util.Collections;
import java.util.List;

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
            // Get the list of movies
            try {
                NewReleasesResponse newReleasesResponse = mRottenTomatoesApi.getNewReleases();
                BeersResponse beersResponse = mOpenBeerDatabaseApi.getBeers();

                mMovies = newReleasesResponse.movies;
                ArrayList<Beer> beers = beersResponse.beers;

                // Sort beers and split into "equal"-length arrays
                List<List<Beer>> beerPartitions = new ArrayList<List<Beer>>();
                Collections.sort(beers);
                Collections.reverse(beers);
                int partitionSize = beers.size() / 10;
                int remainder = beers.size() % 10;
                if (remainder > 0) {
                    partitionSize++;
                }
                for (int i = 0; i < beers.size(); i += partitionSize) {
                    remainder--;
                    if (remainder == -1) {
                        partitionSize--;
                    }
                    int end = Math.min(i + partitionSize, beers.size());
                    List<Beer> sublist = beers.subList(i, end);
                    beerPartitions.add(new ArrayList<Beer>(sublist));
                }

                for (Movie m : mMovies) {
                    m.recommendedBeers = new ArrayList<Beer>();

                    if (m.ratings.criticsScore < 10) {
                        m.recommendedBeers.addAll(beerPartitions.get(0));
                    } else if (m.ratings.criticsScore < 20) {
                        m.recommendedBeers.addAll(beerPartitions.get(1));
                    } else if (m.ratings.criticsScore < 30) {
                        m.recommendedBeers.addAll(beerPartitions.get(2));
                    } else if (m.ratings.criticsScore < 40) {
                        m.recommendedBeers.addAll(beerPartitions.get(3));
                    } else if (m.ratings.criticsScore < 50) {
                        m.recommendedBeers.addAll(beerPartitions.get(4));
                    } else if (m.ratings.criticsScore < 60) {
                        m.recommendedBeers.addAll(beerPartitions.get(5));
                    } else if (m.ratings.criticsScore < 70) {
                        m.recommendedBeers.addAll(beerPartitions.get(6));
                    } else if (m.ratings.criticsScore < 80) {
                        m.recommendedBeers.addAll(beerPartitions.get(7));
                    } else if (m.ratings.criticsScore < 90) {
                        m.recommendedBeers.addAll(beerPartitions.get(8));
                    } else {
                        m.recommendedBeers.addAll(beerPartitions.get(9));
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
