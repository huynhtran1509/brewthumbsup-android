package com.willowtreeapps.brewthumbsup.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * User: derek Date: 11/22/13 Time: 2:38 PM
 */
public class Beer implements Comparable<Beer> {

    @SerializedName("id") public int id;
    @SerializedName("name") public String name;
    @SerializedName("description") public String description;
    @SerializedName("abv") public String abv;
    @SerializedName("created_at") public String createdAt;
    @SerializedName("updated_at") public String updatedAt;
    @SerializedName("brewery") public Brewery brewery;

    @Override
    public int compareTo(Beer beer) {
        return Float.valueOf(abv).compareTo(Float.valueOf(beer.abv));
    }
}
