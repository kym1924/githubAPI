package com.retrofit.github.data.api.search

import com.retrofit.github.data.model.Search
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchDataSource : SearchDataSource
){
    suspend fun getSearch() = searchDataSource.getSearch()

    suspend fun insert(search : Search) {
        searchDataSource.insert(search)
    }

    suspend fun delete(search : Search) {
        searchDataSource.delete(search)
    }

    suspend fun requestUsers(q : String) = searchDataSource.requestUsers(q)

    suspend fun requestRepos(q : String) = searchDataSource.requestRepos(q)
}
