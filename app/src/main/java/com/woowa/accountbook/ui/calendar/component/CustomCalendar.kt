package com.woowa.accountbook.ui.calendar.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.woowa.accountbook.utils.DateUtil

@ExperimentalFoundationApi
@Composable
fun CustomCalendar(
    year: Int,
    month: Int,
    calendarData: List<Pair<Int, Int>>,
    modifier: Modifier = Modifier,
) {
    // 1. 요일을 받아온다.
    // 2. 저번 달의 마지막 일 수를 받아온다.
    // 3. 일주일 중 첫날의 요일을 계산해서 그 수만큼 뺀다.
    // 4. 추가한다.
    val firstDayOfWeek = DateUtil.getDayOfWeekCode(year, month, 1)
    val currentMaxDay = DateUtil.getLastDayOfMonth(year, month)

    val (prevYear, prevMonth) = DateUtil.adjustYearAndMonth(year, month.minus(1))
    val prevMaxDay = DateUtil.getLastDayOfMonth(prevYear, prevMonth)
    var prevVisibleDay = prevMaxDay.minus(firstDayOfWeek).plus(2)

    val prevCount = firstDayOfWeek.minus(1)
    val nextCount = (7 - DateUtil.getDayOfWeekCode(year, month, currentMaxDay))
    var day = 1
    var nextDay = 1
    // 현재 30일 28일 경우에 문제가 발생함
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
                expenditure = calendarData[day].second
            )
            day++
        }
        items(nextCount) {
            CustomCalendarItem(day = nextDay, isVisible = false)
            nextDay++
        }
    }
}