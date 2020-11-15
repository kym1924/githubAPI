package com.example.retrofitpractice.util.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.retrofitpractice.data.model.Search

@Dao
interface SearchDao {
    @Query("SELECT * from searchDb")
    fun getAll() : LiveData<List<Search>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(search : Search)

    @Delete
    suspend fun delete(search : Search)
}