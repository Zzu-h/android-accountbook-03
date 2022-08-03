package com.woowa.accountbook.data.repository

import com.woowa.accountbook.data.datasource.AccountBookManageDataSource
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.domain.model.History
import com.woowa.accountbook.domain.model.Payment
import com.woowa.accountbook.domain.repository.AccountBookManageRepository
import javax.inject.Inject

class AccountBookManageRepositoryImpl @Inject constructor(
    private val accountBookManageDataSource: AccountBookManageDataSource
) : AccountBookManageRepository {

    override suspend fun addPayment(payment: Payment): Result<Boolean> =
        runCatching {
            var flag = false
            accountBookManageDataSource.addPayment(payment.toPaymentDto())
                .onSuccess { flag = it }
                .onFailure { flag = false }
            flag
        }

    override suspend fun updatePayment(newPayment: Payment): Result<Boolean> =
        runCatching {
            var flag = false
            accountBookManageDataSource.updatePayment(newPayment.toPaymentDto())
                .onSuccess { flag = it }
                .onFailure { flag = false }
            flag
        }

    override suspend fun addCategory(categoryDto: Category): Result<Boolean> =
        runCatching {
            var flag = false
            accountBookManageDataSource.addCategory(categoryDto.toCategoryDto())
                .onSuccess { flag = it }
                .onFailure { flag = false }
            flag
        }

    override suspend fun updateCategory(newCategory: Category): Result<Boolean> =
        runCatching {
            var flag = false
            accountBookManageDataSource.updateCategory(newCategory.toCategoryDto())
                .onSuccess { flag = it }
                .onFailure { flag = false }
            flag
        }

    override suspend fun addHistory(history: History): Result<Boolean> =
        runCatching {
            var flag = false
            accountBookManageDataSource.addHistory(history.toHistoryDto())
                .onSuccess { flag = it }
                .onFailure { flag = false }
            flag
        }

    override suspend fun updateHistory(history: History): Result<Boolean> =
        runCatching {
            var flag = false
            accountBookManageDataSource.updateHistory(history.toHistoryDto())
                .onSuccess { flag = it }
                .onFailure { flag = false }
            flag
        }

    override suspend fun removeHistoryList(historyList: List<History>): Result<Boolean> =
        runCatching {
            var flag = false
            accountBookManageDataSource.removeHistoryList(historyList.map { it.toHistoryDto() })
                .onSuccess { flag = it }
                .onFailure { flag = false }
            flag
        }
}