package com.woowa.accountbook.data.datasource

import com.woowa.accountbook.data.dto.CategoryDto
import com.woowa.accountbook.data.dto.HistoryDto
import com.woowa.accountbook.data.dto.PaymentDto
import com.woowa.accountbook.data.local.LocalApiDto
import javax.inject.Inject

class AccountBookManageDataSource @Inject constructor(private val localApiDto: LocalApiDto) {

    fun addPayment(paymentDto: PaymentDto): Result<Boolean> =
        runCatching { localApiDto.addPayment(paymentDto) }

    fun updatePayment(newPayment: PaymentDto): Result<Boolean> =
        runCatching { localApiDto.updatePayment(newPayment) }

    fun addCategory(categoryDto: CategoryDto): Result<Boolean> =
        runCatching { localApiDto.addCategory(categoryDto) }

    fun updateCategory(newCategory: CategoryDto): Result<Boolean> =
        runCatching { localApiDto.updateCategory(newCategory) }

    fun addHistory(historyDto: HistoryDto): Result<Boolean> =
        runCatching { localApiDto.addHistory(historyDto) }

    fun updateHistory(historyDto: HistoryDto): Result<Boolean> =
        runCatching { localApiDto.updateHistory(historyDto) }

    fun removeHistoryList(historyDto: List<HistoryDto>): Result<Boolean> =
        runCatching { localApiDto.removeHistoryList(historyDto) }
}