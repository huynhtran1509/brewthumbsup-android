package com.willowtreeapps.brewthumbsup;

import com.google.inject.Injector;

import com.willowtreeapps.brewthumbsup.api.OpenBeerDatabaseApi;
import com.willowtreeapps.brewthumbsup.api.RottenTomatoesApi;
import com.willowtreeapps.brewthumbsup.api.response.BeersResponse;
import com.willowtreeapps.brewthumbsup.api.response.NewReleasesResponse;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;

import roboguice.RoboGuice;

/**
 * User: derek Date: 11/22/13 Time: 2:25 PM
 */
@RunWith(RobolectricTestRunner.class)
public class ApiTest {

    RottenTomatoesApi rottenTomatoesApi;
    OpenBeerDatabaseApi openBeerDatabaseApi;

    @Before
    public void setup() {
        Injector i = RoboGuice.getBaseApplicationInjector(Robolectric.application);
        rottenTomatoesApi = i.getInstance(RottenTomatoesApi.class);
        openBeerDatabaseApi = i.getInstance(OpenBeerDatabaseApi.class);
        Robolectric.getFakeHttpLayer().interceptHttpRequests(false);
    }

    @Test
    public void getNewReleases() {
        try {
            NewReleasesResponse response = rottenTomatoesApi.getNewReleases();
            Assert.assertNotNull(response.movies);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.toString());
        }
    }

    @Test
    public void getBeers() {
        try {
            BeersResponse response = openBeerDatabaseApi.getBeers(7);
            Assert.assertNotNull(response.beers);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail(e.toString());
        }
    }
}
