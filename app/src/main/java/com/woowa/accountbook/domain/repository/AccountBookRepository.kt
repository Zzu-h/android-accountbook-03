package com.woowa.accountbook.domain.repository

import com.woowa.accountbook.domain.model.Account
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.domain.model.Payment

interface AccountBookRepository {

    suspend fun getAllHistory(year: Int, month: Int): Result<List<Account>>
    suspend fun getAllCategory(): Result<List<Category>>
    suspend fun getAllPayment(): Result<List<Payment>>
}