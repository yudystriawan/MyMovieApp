package com.example.mymovieapp.helper

import android.database.Cursor
import com.example.mymovieapp.database.DatabaseContract
import com.example.mymovieapp.model.FavoriteMovie
import com.example.mymovieapp.model.FavoriteTv

object MappingHelper {

    fun getAllMovies(cursor: Cursor): ArrayList<FavoriteMovie> {
        val movies = ArrayList<FavoriteMovie>()

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns._ID))
                val movieId =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.MOVIE_ID))
                val posterPath =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.POSTER_PATH))
                movies.add(FavoriteMovie(id, movieId, posterPath))
                cursor.moveToNext()
            }
        }

        return movies
    }

    fun getAllTvs(cursor: Cursor): ArrayList<FavoriteTv> {
        val tvShows = ArrayList<FavoriteTv>()

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast) {
                val id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavTvColumns._ID))
                val tvId =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavTvColumns.TV_ID))
                val posterPath =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavTvColumns.POSTER_PATH))
                tvShows.add(FavoriteTv(id, tvId, posterPath))
                cursor.moveToNext()
            }
        }

        return tvShows
    }

    fun mapCursorToObject(cursor: Cursor): FavoriteMovie {

        var favoriteMovie = FavoriteMovie()

        if (cursor.moveToNext()) {
            val id =
                cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns._ID))
            val movieId =
                cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.MOVIE_ID))
            val posterPath =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavMovieColumns.POSTER_PATH))

            favoriteMovie = FavoriteMovie(id, movieId, posterPath)
        }

        return favoriteMovie

    }

    fun getTv(cursor: Cursor): FavoriteTv {

        var favoriteTv = FavoriteTv()

        if (cursor.moveToNext()) {
            val id =
                cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavTvColumns._ID))
            val tvId =
                cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavTvColumns.TV_ID))
            val posterPath =
                cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavTvColumns.POSTER_PATH))

            favoriteTv = FavoriteTv(id, tvId, posterPath)
        }

        return favoriteTv

    }
}