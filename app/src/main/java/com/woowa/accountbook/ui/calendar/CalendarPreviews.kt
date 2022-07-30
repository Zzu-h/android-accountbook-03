package com.woowa.accountbook.ui.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.woowa.accountbook.ui.calendar.component.CalendarTotText
import com.woowa.accountbook.ui.calendar.component.CustomCalendar
import com.woowa.accountbook.ui.theme.AccountbookTheme
import com.woowa.accountbook.ui.theme.Teal200

@ExperimentalFoundationApi
@Preview
@Composable
fun CalendarPreviews() {
    AccountbookTheme {
        CustomCalendar(year = 2022, month = 7, calendarData = List(31) { Pair(30231, 585959) })
    }
}

@Preview
@Composable
fun CalendarTotTextPreview() {
    AccountbookTheme {
        CalendarTotText("수입", "123122321", color = Teal200)
    }
}
