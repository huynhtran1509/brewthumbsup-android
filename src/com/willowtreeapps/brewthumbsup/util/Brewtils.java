package com.willowtreeapps.brewthumbsup.util;

import com.google.common.collect.ImmutableMap;

import com.willowtreeapps.brewthumbsup.R;

import java.util.Map;

/**
 * User: derek Date: 11/25/13 Time: 2:36 PM
 */
public class Brewtils {

    public static final Map<String, Integer> MPAA_RATING_MAP = new ImmutableMap.Builder<String, Integer>()
            .put("G", R.drawable.img_mpaa_g)
            .put("PG", R.drawable.img_mpaa_pg)
            .put("PG-13", R.drawable.img_mpaa_pg13)
            .put("R", R.drawable.img_mpaa_r)
            .put("NC-17", R.drawable.img_mpaa_nc17)
            .put("Unrated", R.drawable.img_mpaa_nc17)
            .build();
}
