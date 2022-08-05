package com.woowa.accountbook.data.repository

import com.woowa.accountbook.data.datasource.AccountBookDataSource
import com.woowa.accountbook.data.dto.toAccount
import com.woowa.accountbook.data.dto.toCategory
import com.woowa.accountbook.data.dto.toPayment
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.domain.model.History
import com.woowa.accountbook.domain.model.Payment
import com.woowa.accountbook.domain.repository.AccountBookRepository
import javax.inject.Inject

class AccountBookRepositoryImpl @Inject constructor(
    private val accountBookDataSource: AccountBookDataSource
) : AccountBookRepository {

    override suspend fun getAllHistory(year: Int, month: Int): Result<List<History>> {
        val data = accountBookDataSource.getAllHistory(year, month).getOrThrow()
        return runCatching { data.map { it.toAccount() } }
    }

    override suspend fun getAllCategory(): Result<List<Category>> {
        val data = accountBookDataSource.getAllCategory().getOrThrow()
        return runCatching { data.map { it.toCategory() } }
    }

    override suspend fun getAllPayment(): Result<List<Payment>> {
        val data = accountBookDataSource.getAllPayment().getOrThrow()
        return runCatching { data.map { it.toPayment() } }
    }
}