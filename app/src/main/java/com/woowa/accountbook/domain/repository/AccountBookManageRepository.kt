package com.woowa.accountbook.domain.repository

import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.domain.model.History
import com.woowa.accountbook.domain.model.Payment

interface AccountBookManageRepository {

    suspend fun addPayment(paymentDto: Payment): Result<Boolean>
    suspend fun updatePayment(newPayment: Payment): Result<Boolean>
    suspend fun addCategory(categoryDto: Category): Result<Boolean>
    suspend fun updateCategory(newCategory: Category): Result<Boolean>
    suspend fun addHistory(historyDto: History): Result<Boolean>
    suspend fun updateHistory(historyDto: History): Result<Boolean>
    suspend fun removeHistoryList(historyList: List<History>): Result<Boolean>
}