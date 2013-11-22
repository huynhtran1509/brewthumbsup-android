package com.willowtreeapps.brewthumbsup.api;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;

import com.squareup.okhttp.OkHttpClient;
import com.willowtreeapps.brewthumbsup.api.response.NewReleasesResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * User: derek Date: 11/22/13 Time: 2:27 PM
 */
public class RottenTomatoesApi {

    private OkHttpClient mOkHttpClient;
    private Gson mGson;

    private static final String API_KEY = "q6zwefpee9j55g7ztya8camb";

    public static final String NEW_RELEASES_URL =
            "http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json?apikey=" + API_KEY;

    @Inject
    public RottenTomatoesApi() {
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

    public NewReleasesResponse getNewReleases() throws IOException {
        HttpURLConnection connection = mOkHttpClient.open(new URL(NEW_RELEASES_URL));
        NewReleasesResponse response = readJsonToObject(connection, NewReleasesResponse.class);
        return response;
    }
}
