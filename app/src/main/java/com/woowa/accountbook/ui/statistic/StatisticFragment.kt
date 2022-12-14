package com.woowa.accountbook.ui.statistic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.woowa.accountbook.R
import com.woowa.accountbook.ui.AccountBookViewModel
import com.woowa.accountbook.ui.common.component.MainAppBar
import com.woowa.accountbook.ui.common.component.MainDivider
import com.woowa.accountbook.ui.common.popup.MonthYearPickerDialog
import com.woowa.accountbook.ui.statistic.component.CategoryStatisticItem
import com.woowa.accountbook.ui.statistic.component.GraphComponent
import com.woowa.accountbook.ui.theme.AccountbookTheme
import com.woowa.accountbook.ui.theme.Red
import com.woowa.accountbook.utils.StringUtil

class StatisticFragment : Fragment() {

    private val accountBookViewModel: AccountBookViewModel by activityViewModels()
    private val statisticViewModel: StatisticViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_statistic, container, false)

        rootView.findViewById<ComposeView>(R.id.cv_tool_bar).apply {
            setContent {
                val title by statisticViewModel.statisticTitle.observeAsState("hihi")

                AccountbookTheme() {
                    MainAppBar(
                        title = title,
                        onNextIconPressed = { accountBookViewModel.plusMonth() },
                        onPrevIconPressed = { accountBookViewModel.minusMonth() },
                        onTitlePressed = {
                            MonthYearPickerDialog(
                                statisticViewModel.year,
                                statisticViewModel.month
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

        rootView.findViewById<ComposeView>(R.id.cv_statistic_content).apply {
            setContent {
                val totalHistory by statisticViewModel.totalHistory.observeAsState(0)
                val categoryStatisticDto by statisticViewModel.statisticData.observeAsState(
                    emptyList()
                )
                AccountbookTheme {
                    Column {
                        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                            Text(
                                "?????? ??? ??? ?????? ??????", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.primary
                            )
                            Text(
                                StringUtil.getMoneyFormatString(totalHistory),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End,
                                color = Red
                            )
                        }
                        MainDivider()
                        GraphComponent(categoryStatisticDto)
                        LazyColumn {
                            items(categoryStatisticDto) { item -> CategoryStatisticItem(item = item) }
                        }
                        MainDivider()
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
    }

    private fun observeData() {
        accountBookViewModel.totalCategoryList.observe(this.viewLifecycleOwner) { data ->
            statisticViewModel.setCategoryData(data)
        }
        accountBookViewModel.totalHistoryList.observe(this.viewLifecycleOwner) { data ->
            statisticViewModel.setHistoryData(data)
        }
        accountBookViewModel.month.observe(this.viewLifecycleOwner) { month ->
            statisticViewModel.year = accountBookViewModel.year.value ?: 2022
            statisticViewModel.month = month
            val createTitle = "${statisticViewModel.year}??? ${month}???"

            statisticViewModel.statisticTitle.value = createTitle
        }
    }
}