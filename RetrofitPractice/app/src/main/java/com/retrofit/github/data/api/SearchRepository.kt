package com.retrofit.github.data.api

import com.retrofit.github.data.model.Search
import com.retrofit.github.data.room.SearchDao

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
