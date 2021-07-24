package com.example.myinstagramclone.WebService;

import com.example.myinstagramclone.model.PixabayPosts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {

    @GET("?key=20103943-432378ad3ccb78b05090ddc4b&q=birds&image_type=photo")
    Call<PixabayPosts> getAllPosts();

    @GET("?key=20103943-432378ad3ccb78b05090ddc4b&image_type=photo")
    Call<PixabayPosts> getPostByKeyword(@Query("q") String keyWord);
}
