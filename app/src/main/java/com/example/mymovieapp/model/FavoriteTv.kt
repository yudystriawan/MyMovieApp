package com.example.mymovieapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteTv(
    var id: Int = 0,
    var tvId: Int = 0,
    var posterPath: String? = null
) : Parcelable