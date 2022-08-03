package com.woowa.accountbook.domain.repository

import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.domain.model.History
import com.woowa.accountbook.domain.model.Payment

interface AccountBookManageRepository {

    suspend fun getAllHistory(year: Int, month: Int): Result<List<History>>
    suspend fun getAllCategory(): Result<List<Category>>
    suspend fun getAllPayment(): Result<List<Payment>>
}