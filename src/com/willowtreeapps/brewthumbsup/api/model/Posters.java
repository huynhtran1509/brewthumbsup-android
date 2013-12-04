package com.willowtreeapps.brewthumbsup.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * User: derek Date: 11/25/13 Time: 9:36 AM
 */
public class Posters {

    @SerializedName("thumbnail") public String thumbnail;
    @SerializedName("profile") public String profile;
    @SerializedName("detailed") public String detailed;
    @SerializedName("original") public String original;
}
