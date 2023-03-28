package com.example.camera;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Retro_interface {
    @POST("/login")
    Call<helperclass> getimages(@Body helperclass h);

    @POST("/signup")
    Call<Messege> signup(@Body helperclass h);
}
