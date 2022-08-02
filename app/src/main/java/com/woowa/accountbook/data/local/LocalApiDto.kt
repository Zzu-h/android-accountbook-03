package com.woowa.accountbook.data.local

import com.woowa.accountbook.data.dto.HistoryDto
import com.woowa.accountbook.data.dto.toDBHistory
import com.woowa.accountbook.data.local.dao.CategoryDao
import com.woowa.accountbook.data.local.dao.HistoryDao
import com.woowa.accountbook.data.local.dao.PaymentDao
import com.woowa.accountbook.data.local.entity.toHistoryDto
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

    fun addHistory(historyDto: HistoryDto): Boolean {
        return historyDao.addHistory(historyDto.toDBHistory())
    }
}