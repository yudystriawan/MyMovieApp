package com.example.mymovieapp

import com.example.mymovieapp.model.Movie
import com.example.mymovieapp.model.Tv
import com.example.mymovieapp.response.MovieResponse
import com.example.mymovieapp.response.TvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {

    /*Movie API*/

    @GET("movie/now_playing")
    fun getNowPlayingMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<MovieResponse>

    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<MovieResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcomingMovie(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getDetailsMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Movie>

    /*TV Show API*/

    @GET("tv/airing_today")
    fun getAiringToday(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<TvResponse>

    @GET("tv/on_the_air")
    fun getTvOnTheAir(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<TvResponse>

    @GET("tv/popular")
    fun getPopularTv(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<TvResponse>

    @GET("tv/top_rated")
    fun getTopRatedTv(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<TvResponse>

    @GET("tv/{tv_id}")
    fun getDetailsTv(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Tv>
}