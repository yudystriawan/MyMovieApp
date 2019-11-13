package com.example.mymovieapp.response

import com.example.mymovieapp.model.Movie
import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: ArrayList<Movie>,
    @SerializedName("total_result") val totalResult: Int,
    @SerializedName("total_page") val totalPage: Int
)
