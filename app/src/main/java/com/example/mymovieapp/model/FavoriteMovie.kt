package com.example.mymovieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteMovie(
    var id: Int = 0,
    var movieId: Int = 0,
    var posterPath: String? = null
) : Parcelable