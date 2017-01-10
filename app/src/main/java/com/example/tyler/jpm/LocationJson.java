package com.example.tyler.jpm;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


/**
 * Created by Tyler on 1/9/2017.
 * Container for the location objects returned by the api
 */
public class LocationJson {
    @SerializedName("errors")
    public ArrayList<String> errors;

    @SerializedName("locations")
    public ArrayList<AtmJson> locations;
}
