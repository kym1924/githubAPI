package com.example.retrofitpractice.data.api

import com.example.retrofitpractice.data.model.Search
import com.example.retrofitpractice.util.room.SearchDao

class SearchRepository(private val searchDao : SearchDao) {

    fun getAll() = searchDao.getAll()

    suspend fun insert(search : Search) {
        searchDao.insert(search)
    }

    suspend fun delete(search : Search) {
        searchDao.delete(search)
    }
}
