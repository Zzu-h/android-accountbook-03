package com.woowa.accountbook.data.local

import com.woowa.accountbook.data.dto.*
import com.woowa.accountbook.data.local.dao.CategoryDao
import com.woowa.accountbook.data.local.dao.HistoryDao
import com.woowa.accountbook.data.local.dao.PaymentDao
import com.woowa.accountbook.data.local.entity.toCategoryDto
import com.woowa.accountbook.data.local.entity.toHistoryDto
import com.woowa.accountbook.data.local.entity.toPaymentDto
import com.woowa.accountbook.utils.DateUtil
import com.woowa.accountbook.utils.TypeFilter
import javax.inject.Inject

class LocalApiDto @Inject constructor(
    private val historyDao: HistoryDao,
    private val categoryDao: CategoryDao,
    private val paymentDao: PaymentDao
) {

    fun getAllHistory(
        year: Int = DateUtil.currentYear,
        month: Int = DateUtil.currentMonth,
        filter: String = TypeFilter.ALL,
    ): List<HistoryDto> {
        return historyDao.getAllHistoryByMonth(filter, year, month)
            .map { it.toHistoryDto() }
    }

    fun addHistory(historyDto: HistoryDto): Boolean =
        historyDao.addHistory(historyDto.toDBHistory())


    fun getAllCategory(
        filter: String = TypeFilter.ALL,
    ): List<CategoryDto> = categoryDao.getAllCategory(filter)
        .map { it.toCategoryDto() }

    fun addCategory(categoryDto: CategoryDto): Boolean =
        categoryDao.addCategory(categoryDto.toDBCategory())

    fun updateCategory(newCategory: CategoryDto): Boolean =
        categoryDao.updateCategory(newCategory.toDBCategory())

    fun getAllPayment(
        year: Int = DateUtil.currentYear,
        month: Int = DateUtil.currentMonth,
        filter: String = TypeFilter.ALL,
    ): List<PaymentDto> =
        paymentDao.getAllPayment(filter)
            .map { it.toPaymentDto() }

    fun addPayment(paymentDto: PaymentDto): Boolean =
        paymentDao.addPayment(paymentDto.toDBPayment())

    fun updatePayment(newPayment: PaymentDto): Boolean =
        paymentDao.updatePayment(newPayment.toDBPayment())
}