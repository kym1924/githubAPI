package com.retrofit.github.di

import com.retrofit.github.data.api.search.SearchDataSource
import com.retrofit.github.data.api.search.SearchDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModuleBinds {
    @Binds
    abstract fun bindSearchDataSource(searchDataSourceImpl : SearchDataSourceImpl) : SearchDataSource
}