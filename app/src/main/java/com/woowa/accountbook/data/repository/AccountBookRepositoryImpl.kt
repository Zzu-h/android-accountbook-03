package com.woowa.accountbook.data.repository

import com.woowa.accountbook.data.datasource.AccountBookDataSource
import com.woowa.accountbook.data.dto.toExpenditure
import com.woowa.accountbook.data.dto.toIncome
import com.woowa.accountbook.domain.model.Expenditure
import com.woowa.accountbook.domain.model.Income
import com.woowa.accountbook.domain.repository.AccountBookRepository
import javax.inject.Inject

class AccountBookRepositoryImpl @Inject constructor(
    private val accountBookDataSource: AccountBookDataSource
) : AccountBookRepository {

    override suspend fun getIncomeHistory(year: Int, month: Int): Result<List<Income>> {
        val data = accountBookDataSource.getIncomeHistory(year, month).getOrThrow()
        return runCatching { data.map { it.toIncome() } }
    }

    override suspend fun getExpenditureHistory(year: Int, month: Int): Result<List<Expenditure>> {
        val data = accountBookDataSource.getExpenditureHistory(year, month).getOrThrow()
        return runCatching { data.map { it.toExpenditure() } }
    }
}