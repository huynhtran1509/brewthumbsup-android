package com.willowtreeapps.brewthumbsup.api.response;

import com.google.gson.annotations.SerializedName;

import com.willowtreeapps.brewthumbsup.api.model.Beer;

import java.util.ArrayList;

/**
 * User: derek Date: 11/22/13 Time: 2:38 PM
 */
public class BeersResponse extends BaseResponse {

    @SerializedName("currentPage") public int currentPage;
    @SerializedName("numberOfPages") public int numberOfPages;
    @SerializedName("totalResults") public int totalResults;
    @SerializedName("data") public ArrayList<Beer> beers;
}
