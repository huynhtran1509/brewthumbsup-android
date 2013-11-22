package com.willowtreeapps.brewthumbsup.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * User: derek Date: 11/22/13 Time: 2:39 PM
 */
public class Brewery {

    @SerializedName("id") public int id;
    @SerializedName("name") public String name;
    @SerializedName("url") public String url;
    @SerializedName("created_at") public String createdAt;
    @SerializedName("updated_at") public String updatedAt;
}
