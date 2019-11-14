package com.example.mymovieapp.database

import android.provider.BaseColumns

internal class DatabaseContract {

    internal class FavMovieColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "favMovie"
            const val _ID = "_id"
            const val MOVIE_ID = "movie_id"
            const val POSTER_PATH = "poster_path"
        }
    }
    internal class FavTvColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "favTv"
            const val _ID = "_id"
            const val TV_ID = "tv_id"
            const val POSTER_PATH = "poster_path"
        }
    }
}