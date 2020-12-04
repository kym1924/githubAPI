package com.retrofit.github.data.api.search

import com.retrofit.github.data.api.RequestInterface
import com.retrofit.github.data.model.Search
import com.retrofit.github.data.room.SearchDao
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val requestInterface : RequestInterface,
    private val searchDao : SearchDao
) : SearchDataSource {

    override suspend fun getSearch() = searchDao.getSearch()

    override suspend fun insert(search: Search) {
        searchDao.insert(search)
    }

    override suspend fun delete(search: Search) {
        searchDao.delete(search)
    }

    override suspend fun requestUsers(q: String) = requestInterface.requestUsers(q)

    override suspend fun requestRepos(q: String) = requestInterface.requestRepos(q)
}