package com.example.mymovieapp.helper

import android.database.Cursor
import com.example.mymovieapp.database.DatabaseContract
import com.example.mymovieapp.model.FavoriteMovie
import com.example.mymovieapp.model.Movie

object MappingHelper {

    fun mapCursorToArrayList(cursor: Cursor): ArrayList<FavoriteMovie> {
        val movies = ArrayList<FavoriteMovie>()

        cursor.moveToFirst()
        while (cursor.moveToNext()) {
            val id =
                cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns._ID))
            val movieId =
                cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.MOVIE_ID))
            val posterPath =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.POSTER_PATH))
            movies.add(FavoriteMovie(id, movieId, posterPath))
        }

        return movies
    }
}