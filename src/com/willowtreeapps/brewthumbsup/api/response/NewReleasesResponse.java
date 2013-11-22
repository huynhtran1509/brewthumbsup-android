package com.willowtreeapps.brewthumbsup.api.response;

import com.google.gson.annotations.SerializedName;

import com.willowtreeapps.brewthumbsup.api.model.Movie;

import java.util.ArrayList;

/**
 * User: derek Date: 11/22/13 Time: 2:29 PM
 */
public class NewReleasesResponse extends BaseResponse {

    @SerializedName("total") public int total;
    @SerializedName("movies") public ArrayList<Movie> movies;
}
