package com.willowtreeapps.brewthumbsup.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * User: derek Date: 11/22/13 Time: 2:38 PM
 */
public class Beer {

    @SerializedName("id") public String id;
    @SerializedName("name") public String name;
    @SerializedName("description") public String description;
    @SerializedName("abv") public String abv;
    @SerializedName("ibu") public String ibu;
    @SerializedName("labels") public Labels labels;
    @SerializedName("styleId") public String styleId;
    @SerializedName("isOrganic") public String isOrganic;
    @SerializedName("status") public String status;
    @SerializedName("statusDisplay") public String statusDisplay;
    @SerializedName("createDate") public String createDate;
    @SerializedName("updateDate") public String updateDate;
    @SerializedName("style") public Style style;
}