package com.woowa.accountbook.data.datasource

import com.woowa.accountbook.data.dto.DBAccountDto
import com.woowa.accountbook.domain.model.AccountType


class AccountBookDataSource {

    suspend fun getAllHistory(year: Int, month: Int): Result<List<DBAccountDto>> {
        return kotlin.runCatching {
            val list = mutableListOf<DBAccountDto>()
            list.add(DBAccountDto(1234, "hi", "content", type = AccountType.INCOME))
            list.add(DBAccountDto(5678, "hello", null, type = AccountType.INCOME))
            list
        }
    }
}