package com.willowtreeapps.brewthumbsup.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * User: derek Date: 11/25/13 Time: 9:38 AM
 */
public class Links {

    @SerializedName("self") public String self;
    @SerializedName("alternate") public String alternate;
    @SerializedName("cast") public String cast;
    @SerializedName("clips") public String clips;
    @SerializedName("reviews") public String reviews;
    @SerializedName("similar") public String similar;
}
