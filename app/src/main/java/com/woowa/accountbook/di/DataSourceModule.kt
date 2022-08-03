package com.woowa.accountbook.di

import com.woowa.accountbook.data.datasource.AccountBookDataSource
import com.woowa.accountbook.data.datasource.AccountBookManageDataSource
import com.woowa.accountbook.data.local.LocalApiDto
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
    fun provideAccountBookDataSource(localApiDto: LocalApiDto): AccountBookDataSource {
        return AccountBookDataSource(localApiDto)
    }

    @Provides
    @Singleton
    fun provideAccountBookManageDataSource(localApiDto: LocalApiDto): AccountBookManageDataSource {
        return AccountBookManageDataSource(localApiDto)
    }
}