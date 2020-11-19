package com.example.retrofitpractice.data.api

import com.example.retrofitpractice.data.model.Search
import com.example.retrofitpractice.data.room.SearchDao

class SearchRepository(private val requestInterface : RequestInterface, private val searchDao : SearchDao) {

    fun getSearch() = searchDao.getSearch()

    suspend fun insert(search : Search) {
        searchDao.insert(search)
    }

    suspend fun delete(search : Search) {
        searchDao.delete(search)
    }

    suspend fun requestUsers(q : String) = requestInterface.requestUsers(q)

    suspend fun requestRepos(q : String) = requestInterface.requestRepos(q)
}
