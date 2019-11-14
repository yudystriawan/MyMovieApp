package com.example.mymovieapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns._ID

internal class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private const val DATABASE_NAME = "db_favorite_film"

        private const val DATABASE_VERSION = 1

        private val SQL_CREATE_TABLE_MOVIE =
            "CREATE TABLE ${DatabaseContract.FavMovieColumns.TABLE_NAME}" +
                    "(${_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${DatabaseContract.FavMovieColumns.MOVIE_ID} INTEGER," +
                    "${DatabaseContract.FavMovieColumns.POSTER_PATH} TEXT)"

        private val SQL_CREATE_TABLE_TV =
            "CREATE TABLE ${DatabaseContract.FavTvColumns.TABLE_NAME}" +
                    "(${_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${DatabaseContract.FavTvColumns.TV_ID} INTEGER," +
                    "${DatabaseContract.FavTvColumns.POSTER_PATH} TEXT)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_MOVIE)
        db?.execSQL(SQL_CREATE_TABLE_TV)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS ${DatabaseContract.FavMovieColumns.TABLE_NAME}")
        db?.execSQL("DROP TABLE IF EXISTS ${DatabaseContract.FavTvColumns.TABLE_NAME}")
        onCreate(db)
    }

}