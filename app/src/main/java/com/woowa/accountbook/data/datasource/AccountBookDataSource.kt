package com.woowa.accountbook.data.datasource

import com.woowa.accountbook.data.dto.DBExpenditureDto
import com.woowa.accountbook.data.dto.DBIncomeDto


class AccountBookDataSource {
    suspend fun getIncomeHistory(year: Int, month: Int): Result<List<DBIncomeDto>>{
        return kotlin.runCatching {
            val list = mutableListOf<DBIncomeDto>()
            list.add(DBIncomeDto(1234,"hi"))
            list.add(DBIncomeDto(5678,"hello"))
            list
        }
    }

    suspend fun getExpenditureHistory(year: Int, month: Int): Result<List<DBExpenditureDto>>{
        return kotlin.runCatching {
            val list = mutableListOf<DBExpenditureDto>()
            list.add(DBExpenditureDto(1234,"hi"))
            list.add(DBExpenditureDto(5678,"hello"))
            list
        }
    }
}