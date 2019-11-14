package com.example.mymovieapp.ui.tv

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymovieapp.APIInterface
import com.example.mymovieapp.model.Tv
import com.example.mymovieapp.response.TvResponse
import com.example.mymovieapp.services.ServiceGenerator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvViewModel : ViewModel() {

    companion object {
        private const val API_KEY = "d8f112a69e3918d618e1be1a274830eb"
    }

    private val apiInterface = ServiceGenerator.getClient().create(APIInterface::class.java)

    private val airingToday = MutableLiveData<ArrayList<Tv>>()
    private val onTheAir = MutableLiveData<ArrayList<Tv>>()
    private val popularTvs = MutableLiveData<ArrayList<Tv>>()
    private val topRatedTvs = MutableLiveData<ArrayList<Tv>>()
    private val detailsTv = MutableLiveData<Tv>()

    fun setAiringToday(language: String) {
        val api = apiInterface.getAiringToday(API_KEY, language)
        api.enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                val result = response.body()?.results
                airingToday.postValue(result)
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }
        })
    }

    fun setOnTheAir(language: String) {
        val api = apiInterface.getTvOnTheAir(API_KEY, language)
        api.enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                val result = response.body()?.results
                onTheAir.postValue(result)
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }
        })
    }

    fun setPopularTv(language: String) {
        val api = apiInterface.getPopularTv(API_KEY, language)
        api.enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                val result = response.body()?.results
                popularTvs.postValue(result)
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }
        })
    }

    fun setTopRatedTv(language: String) {
        val api = apiInterface.getTopRatedTv(API_KEY, language)
        api.enqueue(object : Callback<TvResponse> {
            override fun onResponse(call: Call<TvResponse>, response: Response<TvResponse>) {
                val result = response.body()?.results
                topRatedTvs.postValue(result)
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }
        })
    }

    fun setDetailsTv(tvId: Int, language: String) {
        val api = apiInterface.getDetailsTv(tvId, API_KEY, language)
        api.enqueue(object : Callback<Tv> {
            override fun onResponse(call: Call<Tv>, response: Response<Tv>) {
                val result = response.body()
                detailsTv.postValue(result)
            }

            override fun onFailure(call: Call<Tv>, t: Throwable) {
                Log.e("onFailure", t.message.toString())
            }
        })
    }

    fun getAiringToday(): MutableLiveData<ArrayList<Tv>>{
        return airingToday
    }

    fun getOnTheAir():MutableLiveData<ArrayList<Tv>>{
        return onTheAir
    }

    fun getPopularTv():MutableLiveData<ArrayList<Tv>>{
        return popularTvs
    }

    fun getTopRatedTv():MutableLiveData<ArrayList<Tv>>{
        return topRatedTvs
    }

    fun getDetailsTv():MutableLiveData<Tv>{
        return detailsTv
    }

}