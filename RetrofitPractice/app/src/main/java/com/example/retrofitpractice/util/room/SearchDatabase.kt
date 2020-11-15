package com.example.retrofitpractice.util.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofitpractice.data.model.Search

@Database(entities = [Search::class], version = 1, exportSchema = false)
abstract class SearchDatabase : RoomDatabase() {
    companion object {
        @Volatile private var INSTANCE : SearchDatabase? = null

        fun getDatabase(context : Context) : SearchDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        SearchDatabase::class.java, "searchDb"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun searchDao() : SearchDao
}