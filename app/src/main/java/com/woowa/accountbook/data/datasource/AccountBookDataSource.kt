package com.woowa.accountbook.data.datasource

import com.woowa.accountbook.data.dto.HistoryDto
import com.woowa.accountbook.data.local.LocalApiDto

class AccountBookDataSource(localApiDto: LocalApiDto) {

    suspend fun getAllHistory(year: Int, month: Int): Result<List<HistoryDto>> {
        return kotlin.runCatching {
            val list = mutableListOf<HistoryDto>()
            /*list.add(HistoryDto(1234, "hi", "content", type = TypeFilter.INCOME))
            list.add(HistoryDto(5678, "hello", null, type = TypeFilter.INCOME))*/
            list
        }
    }
}