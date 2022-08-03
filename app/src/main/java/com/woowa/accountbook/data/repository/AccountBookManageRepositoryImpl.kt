package com.woowa.accountbook.data.repository

import com.woowa.accountbook.data.datasource.AccountBookManageDataSource
import com.woowa.accountbook.data.dto.toAccount
import com.woowa.accountbook.data.dto.toCategory
import com.woowa.accountbook.data.dto.toPayment
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.domain.model.History
import com.woowa.accountbook.domain.model.Payment
import com.woowa.accountbook.domain.repository.AccountBookManageRepository
import javax.inject.Inject

class AccountBookManageRepositoryImpl @Inject constructor(
    private val accountBookManageDataSource: AccountBookManageDataSource
) : AccountBookManageRepository {

    override suspend fun getAllHistory(year: Int, month: Int): Result<List<History>> {
        val data = accountBookManageDataSource.getAllHistory(year, month).getOrThrow()
        return runCatching { data.map { it.toAccount() } }
    }

    override suspend fun getAllCategory(): Result<List<Category>> {
        val data = accountBookManageDataSource.getAllCategory().getOrThrow()
        return runCatching { data.map { it.toCategory() } }
    }

    override suspend fun getAllPayment(): Result<List<Payment>> {
        val data = accountBookManageDataSource.getAllPayment().getOrThrow()
        return runCatching { data.map { it.toPayment() } }
    }
}