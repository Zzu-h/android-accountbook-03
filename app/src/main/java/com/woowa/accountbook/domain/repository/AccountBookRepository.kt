package com.woowa.accountbook.domain.repository

import com.woowa.accountbook.domain.model.Expenditure
import com.woowa.accountbook.domain.model.Income

interface AccountBookRepository {

    suspend fun getIncomeHistory(year: Int, month: Int): Result<List<Income>>
    suspend fun getExpenditureHistory(year: Int, month: Int): Result<List<Expenditure>>
}