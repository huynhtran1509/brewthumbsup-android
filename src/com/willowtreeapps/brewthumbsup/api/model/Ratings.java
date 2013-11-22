package com.willowtreeapps.brewthumbsup.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * User: derek Date: 11/22/13 Time: 2:35 PM
 */
public class Ratings {

    @SerializedName("critics_rating") public String criticsRating;
    @SerializedName("critics_score") public int criticsScore;
    @SerializedName("audience_rating") public String audienceRating;
    @SerializedName("audience_score") public int audienceScore;
}
