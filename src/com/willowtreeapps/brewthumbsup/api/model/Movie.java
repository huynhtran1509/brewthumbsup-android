package com.willowtreeapps.brewthumbsup.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * User: derek Date: 11/22/13 Time: 2:32 PM
 */
public class Movie {

    @SerializedName("id") public String id;
    @SerializedName("title") public String title;
    @SerializedName("year") public String year;
    @SerializedName("mpaa_rating") public String mpaaRating;
    @SerializedName("runtime") public int runtime;
    @SerializedName("critics_consensus") public String criticsConsensus;
    @SerializedName("ratings") public Ratings ratings;
    @SerializedName("synopsis") public String synopsis;
    @SerializedName("abridged_cast") public ArrayList<CastMember> abridgedCast;
    @SerializedName("posters") public Posters posters;
    @SerializedName("release_dates") public ReleaseDates releaseDates;
    @SerializedName("links") public Links links;

    public ArrayList<Beer> recommendedBeers;
}
