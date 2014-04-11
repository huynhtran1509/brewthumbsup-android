package com.willowtreeapps.brewthumbsup.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;

import com.squareup.okhttp.OkHttpClient;
import com.willowtreeapps.brewthumbsup.api.response.BeersResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * User: derek Date: 11/22/13 Time: 2:27 PM
 */
public class OpenBeerDatabaseApi {

    private OkHttpClient mOkHttpClient;
    private Gson mGson;

    public static final String BEERS_URL
            = "http://api.brewerydb.com/v2/beers?key=bdbda40fbf06370f71d8468be3f5a39b&abv=%s";

    @Inject
    public OpenBeerDatabaseApi() {
        mOkHttpClient = new OkHttpClient();
    }

    public Gson getGson() {
        mGson = new Gson();
        return mGson;
    }

    private <T> T readJsonToObject(HttpURLConnection connection, Type type) throws IOException, JsonSyntaxException {
        InputStream inputStream = connection.getInputStream();
        T response = getGson().fromJson(new InputStreamReader(inputStream), type);
        connection.disconnect();
        return response;
    }

    public BeersResponse getBeers(int abv) throws IOException {
        HttpURLConnection connection = mOkHttpClient.open(new URL(String.format(BEERS_URL, Integer.toString(abv))));
        BeersResponse response = readJsonToObject(connection, BeersResponse.class);
        return response;
    }
}
