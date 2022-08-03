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
import com.woowa.accountbook.ui.common.component.MainAppBar
import com.woowa.accountbook.ui.common.popup.MonthYearPickerDialog
import com.woowa.accountbook.ui.theme.AccountbookTheme
import com.woowa.accountbook.ui.theme.Purple700
import com.woowa.accountbook.ui.theme.Red
import com.woowa.accountbook.ui.theme.Teal200
import com.woowa.accountbook.utils.StringUtil

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
                val title by calendarViewModel.historyTitle.observeAsState("")

                AccountbookTheme() {
                    MainAppBar(
                        title = title,
                        onNextIconPressed = { accountBookViewModel.plusMonth() },
                        onPrevIconPressed = { accountBookViewModel.minusMonth() },
                        onTitlePressed = {
                            MonthYearPickerDialog(
                                calendarViewModel.year,
                                calendarViewModel.month
                            ).setListener { _, y, m, d ->
                                accountBookViewModel.setCalendar(y, m)
                            }.show(
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
                val year by accountBookViewModel.month.observeAsState(2022)
                val month by accountBookViewModel.month.observeAsState(7)

                val totalHistory by calendarViewModel.totalHistory.observeAsState(Pair(0, 0))
                val calendarData by calendarViewModel.historyCalendar.observeAsState(
                    List(
                        calendarViewModel.maxDate
                    ) { Pair(30231, 585959) })

                AccountbookTheme {
                    Column {
                        CustomCalendar(
                            year = year,
                            month = month,
                            calendarData = calendarData
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        CalendarTotText(
                            title = "수입",
                            price = StringUtil.getPriceToString(totalHistory.first),
                            color = Teal200
                        )
                        CalendarTotText(
                            title = "지출",
                            price = StringUtil.getPriceToString(totalHistory.second),
                            color = Red
                        )
                        CalendarTotText(
                            title = "총합",
                            price = StringUtil.getPriceToString(totalHistory.first - totalHistory.second),
                            color = Purple700
                        )

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        observeData()
    }

    private fun observeData() {
        accountBookViewModel.totalHistoryList.observe(this.viewLifecycleOwner) { data ->
            calendarViewModel.setTotalData(data)
        }
        accountBookViewModel.month.observe(this.viewLifecycleOwner) { month ->
            calendarViewModel.year = accountBookViewModel.year.value ?: 2022
            calendarViewModel.month = month
            val createTitle = "${calendarViewModel.year}년 ${month}월"

            calendarViewModel.historyTitle.value = createTitle
        }
    }
}