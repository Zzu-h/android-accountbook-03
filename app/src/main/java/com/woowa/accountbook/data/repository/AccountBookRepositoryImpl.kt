package com.woowa.accountbook.data.repository

import android.util.Log
import com.woowa.accountbook.data.datasource.AccountBookDataSource
import com.woowa.accountbook.data.dto.toAccount
import com.woowa.accountbook.domain.model.Account
import com.woowa.accountbook.domain.repository.AccountBookRepository
import javax.inject.Inject

class AccountBookRepositoryImpl @Inject constructor(
    private val accountBookDataSource: AccountBookDataSource
) : AccountBookRepository {

    override suspend fun getAllHistory(year: Int, month: Int): Result<List<Account>> {
        val data = accountBookDataSource.getAllHistory(year, month).getOrThrow()
        Log.d("Tester", "getAllHistory: ${data.toString()}")
        return runCatching { data.map { it.toAccount() } }
    }
}