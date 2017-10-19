package com.example.pimpavee.mylazyinstragram.api;

import com.example.pimpavee.mylazyinstragram.model.FollowRequest;
import com.example.pimpavee.mylazyinstragram.model.FollowResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LazyInstragramApi {

    String BASE = "https://us-central1-retrofit-course.cloudfunctions.net";

    @GET("/getProfile")
    Call<UserProfile> getProfile(@Query("user") String user);

    @Headers({"Content-Type:application/json"})
    @POST("/follow")
    Call<FollowResponse> follow(@Body FollowRequest request);

}
