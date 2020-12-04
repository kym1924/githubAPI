package com.retrofit.github.di

import android.app.Application
import com.retrofit.github.data.api.RequestInterface
import com.retrofit.github.data.api.RetrofitBuilder
import com.retrofit.github.data.api.search.SearchDataSource
import com.retrofit.github.data.api.search.SearchRepository
import com.retrofit.github.data.room.SearchDao
import com.retrofit.github.data.room.SearchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DataModule {
    @Provides
    @Singleton
    fun provideSearchDao(application : Application) : SearchDao =
        SearchDatabase.getDatabase(application).searchDao()

    @Provides
    @Singleton
    fun provideRequestInterface(): RequestInterface = RetrofitBuilder.service

    @Provides
    @Singleton
    fun provideSearchRepository(searchDataSourceImpl : SearchDataSource) =
        SearchRepository(searchDataSourceImpl)

}