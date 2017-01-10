package com.example.tyler.jpm;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tyler on 1/9/2017.
 */
public interface Urls {

    @GET("PSRWeb/location/list.action")
    Call<LocationJson> getLocationInfo(@Query("lat")double lat,
                                 @Query("lng")double lng);
}
