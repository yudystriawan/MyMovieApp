package com.example.mymovieapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns._ID
import com.example.mymovieapp.database.DatabaseContract.FavMovieColumns.Companion.MOVIE_ID
import com.example.mymovieapp.database.DatabaseContract.FavMovieColumns.Companion.POSTER_PATH
import com.example.mymovieapp.database.DatabaseContract.FavMovieColumns.Companion.TABLE_NAME

internal class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private const val DATABASE_NAME = "db_favorite_film"

        private const val DATABASE_VERSION = 1

        private val SQL_CREATE_TABLE_MOVIE = "CREATE TABLE $TABLE_NAME" +
                "(${_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$MOVIE_ID INTEGER," +
                "$POSTER_PATH TEXT)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_MOVIE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

}