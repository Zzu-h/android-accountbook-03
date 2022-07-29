package com.woowa.accountbook.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
import com.woowa.accountbook.ui.common.component.AccountInfoPerDayItem
import com.woowa.accountbook.ui.common.component.MainAppBar
import com.woowa.accountbook.ui.common.popup.MonthYearPickerDialog
import com.woowa.accountbook.ui.history.component.HistoryMainFilterButton
import com.woowa.accountbook.ui.theme.AccountbookTheme
import com.woowa.accountbook.utils.DateUtil

class HistoryFragment : Fragment() {

    private val accountBookViewModel: AccountBookViewModel by activityViewModels()
    private val historyViewModel: HistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_history, container, false)

        rootView.findViewById<ComposeView>(R.id.cv_tool_bar).apply {
            setContent {
                val title by historyViewModel.historyTitle.observeAsState("hihi")

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
        rootView.findViewById<ComposeView>(R.id.cv_history_content).apply {
            setContent {
                val calendarData by historyViewModel.historyCalendar.observeAsState()
                val incomeFilter by historyViewModel.incomeFilter.observeAsState(true)
                val expenditureFilter by historyViewModel.expenditureFilter.observeAsState(true)

                AccountbookTheme {
                    Column {
                        HistoryMainFilterButton(
                            isIncomeChecked = incomeFilter,
                            isExpenditureChecked = expenditureFilter,
                            onIncomeButtonPressed = { historyViewModel.filteringIncomeData() },
                            onExpenditureButtonPressed = { historyViewModel.filteringExpenditureData() },
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        )
                        calendarData?.forEachIndexed { date, item ->
                            if (item.isNotEmpty())
                                AccountInfoPerDayItem(
                                    accountList = item,
                                    year = historyViewModel.year,
                                    month = historyViewModel.month,
                                    day = date
                                )
                        }
                    }
                }
            }
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        observeData()
        buttonSetting()
    }

    private fun buttonSetting() {

    }

    private fun observeData() {
        accountBookViewModel.totalList.observe(this.viewLifecycleOwner) { data ->
            historyViewModel.setTotalData(data)
        }
        accountBookViewModel.month.observe(this.viewLifecycleOwner) { month ->
            historyViewModel.year = accountBookViewModel.year.value ?: 2022
            historyViewModel.month = month
            val createTitle = "${historyViewModel.year}년 ${month}월"

            historyViewModel.historyTitle.value = createTitle
        }
    }
}