package com.willowtreeapps.brewthumbsup.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * User: derek Date: 11/22/13 Time: 2:35 PM
 */
public class CastMember {

    @SerializedName("name") public String name;
    @SerializedName("characters") public ArrayList<String> characters;
}
