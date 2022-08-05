package com.woowa.accountbook.ui.calendar.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.woowa.accountbook.ui.theme.AccountbookTheme
import com.woowa.accountbook.utils.DateUtil

@ExperimentalFoundationApi
@Composable
fun CustomCalendar(
    year: Int,
    month: Int,
    calendarData: List<Pair<Int, Int>>,
    modifier: Modifier = Modifier,
) {
    val firstDayOfWeek = DateUtil.getDayOfWeekCode(year, month, 1)
    val currentMaxDay = DateUtil.getLastDayOfMonth(year, month)

    val (prevYear, prevMonth) = DateUtil.adjustYearAndMonth(year, month.minus(1))
    val prevMaxDay = DateUtil.getLastDayOfMonth(prevYear, prevMonth)
    var prevVisibleDay = prevMaxDay.minus(firstDayOfWeek).plus(2)

    val prevCount = firstDayOfWeek.minus(1)
    val nextCount = (7 - DateUtil.getDayOfWeekCode(year, month, currentMaxDay))
    var day = 1
    var nextDay = 1

    LazyVerticalGrid(
        cells = GridCells.Fixed(7),
    ) {
        items(prevCount) {
            CustomCalendarItem(day = prevVisibleDay, isVisible = false)
            prevVisibleDay++
        }
        items(currentMaxDay) {
            CustomCalendarItem(
                day = day,
                income = calendarData[day].first,
                expenditure = calendarData[day].second,
                isToday = (year == DateUtil.currentYear && month == DateUtil.currentMonth && day == DateUtil.currentDay)
            )
            day++
        }
        items(nextCount) {
            CustomCalendarItem(day = nextDay, isVisible = false)
            nextDay++
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun CalendarPreviews() {
    AccountbookTheme {
        CustomCalendar(year = 2022, month = 7, calendarData = List(31) { Pair(30231, 585959) })
    }
}