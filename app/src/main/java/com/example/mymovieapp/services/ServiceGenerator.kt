package com.example.mymovieapp.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {

    companion object {

        private val BASE_URL = "https://api.themoviedb.org/3/"

        fun getClient(): Retrofit {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }
    }

}