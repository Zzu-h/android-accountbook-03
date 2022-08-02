package com.woowa.accountbook.data.local

import com.woowa.accountbook.data.dto.HistoryDto
import com.woowa.accountbook.data.dto.toDBHistory
import com.woowa.accountbook.data.local.dao.CategoryDao
import com.woowa.accountbook.data.local.dao.HistoryDao
import com.woowa.accountbook.data.local.dao.PaymentDao
import com.woowa.accountbook.utils.DateUtil
import com.woowa.accountbook.utils.TypeFilter
import javax.inject.Inject

class LocalApiDto @Inject constructor(
    private val historyDao: HistoryDao,
    private val categoryDao: CategoryDao,
    private val paymentDao: PaymentDao
) {
    fun getAllHistory(
        filter: String = TypeFilter.ALL,
        year: Int = DateUtil.currentYear,
        month: Int = DateUtil.currentMonth
    ): List<HistoryDto> {
        val list = historyDao.getAllHistoryByMonth(filter, year, month)
        val returnList = listOf<HistoryDto>()

        return returnList
    }

    fun addHistory(historyDto: HistoryDto) {
        historyDao.addHistory(historyDto.toDBHistory())
    }
}