package com.woowa.accountbook.utils

import android.os.Build
import java.time.LocalDate
import java.util.*

object DateUtil {
    private val calendar: Calendar = Calendar.getInstance()

    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH) + 1
    val currentDay = calendar.get(Calendar.DATE)
    val currentDayOfWeek by lazy {
        getDayOfWeekKR(calendar.get(Calendar.DAY_OF_WEEK))
    }

    fun getDateToString() {}
    fun getDayOfWeek(year: Int, month: Int, day: Int): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val date = LocalDate.of(year, month, day)
            val dayOfWeek = date.dayOfWeek
            val dayOfWeekNumber = dayOfWeek.value

            getDayOfWeekKR(dayOfWeekNumber)
        } else currentDayOfWeek
    }

    private fun getDayOfWeekKR(code: Int): String =
        when (code) {
            1 -> "월"
            2 -> "화"
            3 -> "수"
            4 -> "목"
            5 -> "금"
            6 -> "토"
            else -> "일"
        } + "요일"
}