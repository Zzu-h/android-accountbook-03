package com.woowa.accountbook.data.datasource

import com.woowa.accountbook.data.dto.CategoryDto
import com.woowa.accountbook.data.dto.HistoryDto
import com.woowa.accountbook.data.dto.PaymentDto
import com.woowa.accountbook.data.local.LocalApiDto

class AccountBookManageDataSource(private val localApiDto: LocalApiDto) {

    suspend fun getAllHistory(year: Int, month: Int): Result<List<HistoryDto>> {
        return runCatching { localApiDto.getAllHistory(year, month) }
    }

    suspend fun getAllCategory(): Result<List<CategoryDto>> {
        return runCatching { localApiDto.getAllCategory() }
    }

    suspend fun getAllPayment(): Result<List<PaymentDto>> {
        return runCatching { localApiDto.getAllPayment() }
    }
}