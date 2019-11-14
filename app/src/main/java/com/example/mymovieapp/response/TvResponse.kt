package com.example.mymovieapp.response

import com.example.mymovieapp.model.Tv
import com.google.gson.annotations.SerializedName

data class TvResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: ArrayList<Tv>,
    @SerializedName("total_result") val totalResult: Int,
    @SerializedName("total_page") val totalPage: Int
)
