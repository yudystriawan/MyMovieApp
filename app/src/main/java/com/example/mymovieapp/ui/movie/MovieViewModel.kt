package com.example.mymovieapp.ui.movie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovieapp.APIInterface
import com.example.mymovieapp.model.Movie
import com.example.mymovieapp.response.MovieResponse
import com.example.mymovieapp.services.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
    companion object {
        private const val API_KEY = "d8f112a69e3918d618e1be1a274830eb"
    }
    private val apiInterface = ServiceGenerator.getClient().create(APIInterface::class.java)

    private val nowPlayingMovies = MutableLiveData<ArrayList<Movie>>()
    private val popularMovies = MutableLiveData<ArrayList<Movie>>()
    private val topRatedMovies = MutableLiveData<ArrayList<Movie>>()
    private val upcomingMovies = MutableLiveData<ArrayList<Movie>>()
    private val detailsMovie = MutableLiveData<Movie>()

    fun setNowPlayingMovies(language: String) {
        val api = apiInterface.getNowPlayingMovie(API_KEY, language)
        api.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val result = response.body()?.results
                nowPlayingMovies.postValue(result)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }
        })
    }

    fun setPopularMovies(language: String) {
        val api = apiInterface.getPopularMovie(API_KEY, language)
        api.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val result = response.body()?.results
                popularMovies.postValue(result)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }
        })
    }

    fun setTopRatedMovies(language: String) {
        val api = apiInterface.getTopRatedMovie(API_KEY, language)
        api.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val result = response.body()?.results
                topRatedMovies.postValue(result)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }
        })
    }

    fun setUpcomingMovies(language: String) {
        val api = apiInterface.getUpcomingMovie(API_KEY, language)
        api.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val result = response.body()?.results
                upcomingMovies.postValue(result)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }
        })
    }

    fun setDetailsMovie(movieId: Int, language: String) {
        val api = apiInterface.getDetailsMovie(movieId, API_KEY, language)
        api.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val result = response.body()
                detailsMovie.postValue(result)
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }
        })
    }

    fun getNowPlayingMovies(): MutableLiveData<ArrayList<Movie>> {
        return nowPlayingMovies
    }

    fun getPopularMovies(): MutableLiveData<ArrayList<Movie>> {
        return popularMovies
    }

    fun getTopRatedMovies(): MutableLiveData<ArrayList<Movie>> {
        return topRatedMovies
    }

    fun getUpcomingMovies(): MutableLiveData<ArrayList<Movie>> {
        return upcomingMovies
    }

    fun getDetailsMovie(): MutableLiveData<Movie> {
        return detailsMovie
    }


}