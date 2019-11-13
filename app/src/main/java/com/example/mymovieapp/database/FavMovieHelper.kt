package com.example.mymovieapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns._ID
import com.example.mymovieapp.database.DatabaseContract.FavMovieColumns.Companion.TABLE_NAME

class FavMovieHelper(context: Context) {

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private lateinit var databaseHelper: DatabaseHelper
        private var INSTANCE: FavMovieHelper? = null

        private lateinit var database: SQLiteDatabase

        fun getInstance(context: Context): FavMovieHelper {
            if (INSTANCE == null) {
                synchronized(SQLiteOpenHelper::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = FavMovieHelper(context)
                    }
                }
            }
            return INSTANCE as FavMovieHelper
        }
    }

    init {
        databaseHelper = DatabaseHelper(context)
    }

    @Throws(SQLException::class)
    fun open() {
        database = databaseHelper.writableDatabase
    }

    fun close() {
        databaseHelper.close()

        if (database.isOpen) database.close()
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC"
        )
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

}