package com.example.niteshgarg.egym.API;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by niteshgarg on 13/02/16.
 */
public interface UserInterface {

    @GET("/")
    public void getUsers(@Query("results") int result_value,
                          Callback<com.example.niteshgarg.egym.model.userPOJO> pojo);
}
