package com.woowa.accountbook.utils

import java.util.*

object DateUtil {
    private val calendar: Calendar = Calendar.getInstance()

    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH) + 1
    val currentDay = calendar.get(Calendar.DATE)
    val currentDayOfWeek by lazy {
        getDayOfWeekKR(calendar.get(Calendar.DAY_OF_WEEK))
    }

    fun getDateToString(
        year: Int = currentYear,
        month: Int = currentMonth,
        day: Int = currentDay
    ): String {
        return "${year}. ${month}. $day ${getDayOfWeek(year, month, day)}"
    }

    fun getDayOfWeekCode(year: Int, month: Int, day: Int): Int {
        val date = Calendar.getInstance()
        date.set(year, month, day)

        return (date.get(Calendar.DAY_OF_WEEK) + 3).mod(7).plus(1)
    }

    fun getDayOfWeek(year: Int, month: Int, day: Int): String {

        return getDayOfWeekKR(getDayOfWeekCode(year, month, day))
    }

    private fun getDayOfWeekKR(code: Int): String =
        when (code) {
            Calendar.MONDAY -> "월"
            Calendar.TUESDAY -> "화"
            Calendar.WEDNESDAY -> "수"
            Calendar.THURSDAY -> "목"
            Calendar.FRIDAY -> "금"
            Calendar.SATURDAY -> "토"
            else -> "일"
        } + "요일"

    fun adjustYearAndMonth(year: Int, month: Int): Pair<Int, Int> {
        var tmpMonth = month
        var tmpYear = year

        if (month <= 0) {
            tmpMonth = 12
            tmpYear -= 1
        } else if (month >= 13) {
            tmpMonth = 1
            tmpYear += 1
        }

        return Pair(tmpYear, tmpMonth)
    }

    fun getLastDayOfMonth(year: Int, month: Int): Int = when (month) {
        2 -> if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) 290 else 20
        1, 3, 5, 7, 8, 10, 12 -> 31
        else -> 30
    }
}