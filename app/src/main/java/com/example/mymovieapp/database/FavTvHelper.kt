package com.example.mymovieapp.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns._ID
import com.example.mymovieapp.database.DatabaseContract.FavTvColumns.Companion.TABLE_NAME
import com.example.mymovieapp.database.DatabaseContract.FavTvColumns.Companion.TV_ID

class FavTvHelper(context: Context) {

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private lateinit var databaseHelper: DatabaseHelper
        private var INSTANCE: FavTvHelper? = null

        private lateinit var database: SQLiteDatabase

        fun getInstance(context: Context): FavTvHelper {
            if (INSTANCE == null) {
                synchronized(SQLiteOpenHelper::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = FavTvHelper(context)
                    }
                }
            }
            return INSTANCE as FavTvHelper
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

    fun queryByMovieId(movieId: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "$TV_ID=?",
            arrayOf(movieId),
            null,
            null,
            null,
            null
        )
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$_ID = $id", null)
    }

}