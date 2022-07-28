package com.woowa.accountbook.di

import com.woowa.accountbook.data.datasource.AccountBookDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideAccountBookDataSource(): AccountBookDataSource {
        return AccountBookDataSource()
    }
}