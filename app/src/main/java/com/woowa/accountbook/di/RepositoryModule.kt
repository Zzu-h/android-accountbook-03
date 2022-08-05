package com.woowa.accountbook.di

import com.woowa.accountbook.data.datasource.AccountBookDataSource
import com.woowa.accountbook.data.datasource.AccountBookManageDataSource
import com.woowa.accountbook.data.repository.AccountBookManageRepositoryImpl
import com.woowa.accountbook.data.repository.AccountBookRepositoryImpl
import com.woowa.accountbook.domain.repository.AccountBookManageRepository
import com.woowa.accountbook.domain.repository.AccountBookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAccountBookRepository(dataSource: AccountBookDataSource): AccountBookRepository {
        return AccountBookRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideAccountBookManageRepository(dataSource: AccountBookManageDataSource): AccountBookManageRepository {
        return AccountBookManageRepositoryImpl(dataSource)
    }
}