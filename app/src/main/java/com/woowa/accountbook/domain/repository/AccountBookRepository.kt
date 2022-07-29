package com.woowa.accountbook.domain.repository

import com.woowa.accountbook.domain.model.Account

interface AccountBookRepository {

    suspend fun getAllHistory(year: Int, month: Int): Result<List<Account>>
}