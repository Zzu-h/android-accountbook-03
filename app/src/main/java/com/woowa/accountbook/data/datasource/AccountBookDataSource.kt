package com.woowa.accountbook.data.datasource

import com.woowa.accountbook.data.dto.HistoryDto
import com.woowa.accountbook.data.local.LocalApiDto

class AccountBookDataSource(private val localApiDto: LocalApiDto) {

    suspend fun getAllHistory(year: Int, month: Int): Result<List<HistoryDto>> {
        return runCatching { localApiDto.getAllHistory(year, month) }
    }
}