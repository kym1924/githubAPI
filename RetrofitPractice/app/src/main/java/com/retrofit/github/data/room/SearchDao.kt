package com.retrofit.github.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.retrofit.github.data.model.Search

@Dao
interface SearchDao {
    @Query("SELECT * from searchDb ORDER BY createdAt DESC")
    fun getSearch() : LiveData<List<Search>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(search : Search)

    @Delete
    suspend fun delete(search : Search)
}