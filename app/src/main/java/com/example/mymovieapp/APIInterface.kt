package com.example.mymovieapp

import com.example.mymovieapp.model.Movie
import com.example.mymovieapp.response.APIResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {

    @GET("movie/now_playing")
    fun getNowPlayingMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<APIResponse>

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<APIResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<APIResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<APIResponse>

    @GET("movie/{movie_id}")
    fun getDetailsMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Movie>
}