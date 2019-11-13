package com.example.mymovieapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("original_title") var originalTitle: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("vote_average") var voteAverage: String? = null,
    @SerializedName("vote_count") var voteCount: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("backdrop_path") var backdropPath: String? = null
) : Parcelable