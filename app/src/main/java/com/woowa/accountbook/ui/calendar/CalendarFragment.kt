package com.woowa.accountbook.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.woowa.accountbook.R
import com.woowa.accountbook.ui.AccountBookViewModel
import com.woowa.accountbook.ui.calendar.component.CalendarTotText
import com.woowa.accountbook.ui.calendar.component.CustomCalendar
import com.woowa.accountbook.ui.common.popup.MonthYearPickerDialog
import com.woowa.accountbook.ui.common.component.MainAppBar
import com.woowa.accountbook.ui.theme.AccountbookTheme
import com.woowa.accountbook.ui.theme.Purple700
import com.woowa.accountbook.ui.theme.Red
import com.woowa.accountbook.ui.theme.Teal200
import com.woowa.accountbook.utils.DateUtil

class CalendarFragment : Fragment() {

    private val accountBookViewModel: AccountBookViewModel by activityViewModels()
    private val calendarViewModel: CalendarViewModel by viewModels()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_calendar, container, false)

        rootView.findViewById<ComposeView>(R.id.cv_tool_bar).apply {
            setContent {
                val title by calendarViewModel.historyTitle.observeAsState("hihi")

                AccountbookTheme() {
                    MainAppBar(
                        title = title,
                        onNextIconPressed = { accountBookViewModel.plusMonth() },
                        onPrevIconPressed = { accountBookViewModel.minusMonth() },
                        onTitlePressed = {
                            MonthYearPickerDialog(DateUtil.currentYear, DateUtil.currentMonth).show(
                                parentFragmentManager,
                                getString(R.string.fragment_calendar)
                            )
                        }
                    )
                }
            }
        }
        rootView.findViewById<ComposeView>(R.id.cv_calendar_content).apply {
            setContent {
                val calendarData by calendarViewModel.historyCalendar.observeAsState()

                AccountbookTheme {
                    Column {
                        CustomCalendar(
                            year = 2022,
                            month = 7,
                            calendarData = List(32) { Pair(30231, 585959) })
                        Spacer(modifier = Modifier.height(16.dp))
                        CalendarTotText(title = "수입", price = "2131", color = Teal200)
                        CalendarTotText(title = "지출", price = "-2131", color = Red)
                        CalendarTotText(title = "총합", price = "2131", color = Purple700)

                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp),
                            color = Purple700,
                        )
                    }
                }
            }
            return rootView
        }
    }
}