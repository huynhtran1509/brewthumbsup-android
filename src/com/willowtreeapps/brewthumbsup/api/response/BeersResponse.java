package com.willowtreeapps.brewthumbsup.api.response;

import com.google.gson.annotations.SerializedName;

import com.willowtreeapps.brewthumbsup.api.model.Beer;

import java.util.ArrayList;

/**
 * User: derek Date: 11/22/13 Time: 2:38 PM
 */
public class BeersResponse extends BaseResponse {

    @SerializedName("page") public int page;
    @SerializedName("pages") public int pages;
    @SerializedName("total") public int total;
    @SerializedName("beers") public ArrayList<Beer> beers;
}
