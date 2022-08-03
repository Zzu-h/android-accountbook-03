package com.woowa.accountbook.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.woowa.accountbook.R
import com.woowa.accountbook.ui.AccountBookViewModel
import com.woowa.accountbook.ui.common.component.AccountInfoPerDayItem
import com.woowa.accountbook.ui.common.component.MainAppBar
import com.woowa.accountbook.ui.common.component.SubAppBar
import com.woowa.accountbook.ui.common.popup.MonthYearPickerDialog
import com.woowa.accountbook.ui.history.component.HistoryMainFilterButton
import com.woowa.accountbook.ui.history.manage.ManageHistoryFragment
import com.woowa.accountbook.ui.theme.AccountbookTheme
import java.io.Serializable

class HistoryFragment : Fragment() {

    private val accountBookViewModel: AccountBookViewModel by activityViewModels()
    private val historyViewModel: HistoryViewModel by viewModels()

    private lateinit var floatingButton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_history, container, false)
        floatingButton = rootView.findViewById(R.id.fab_add_account)

        rootView.findViewById<ComposeView>(R.id.cv_tool_bar).apply {
            setContent {
                val title by historyViewModel.historyTitle.observeAsState("hihi")
                val editFlag by historyViewModel.editFlag.observeAsState(false)
                val trashList by historyViewModel.trashList.observeAsState(emptyList())

                AccountbookTheme() {
                    if (editFlag)
                        SubAppBar(
                            title = "${trashList.size}개 선택",
                            trashButtonActivate = true,
                            onBackIconPressed = {},
                            onTrashIconPressed = {}
                        )
                    else
                        MainAppBar(
                            title = title,
                            onNextIconPressed = { accountBookViewModel.plusMonth() },
                            onPrevIconPressed = { accountBookViewModel.minusMonth() },
                            onTitlePressed = {
                                MonthYearPickerDialog(
                                    historyViewModel.year,
                                    historyViewModel.month
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
        rootView.findViewById<ComposeView>(R.id.cv_history_content).apply {
            setContent {
                val calendarData by historyViewModel.historyCalendar.observeAsState()
                val incomeFilter by historyViewModel.incomeFilter.observeAsState(true)
                val expenditureFilter by historyViewModel.expenditureFilter.observeAsState(true)
                val emptyFlag by historyViewModel.emptyFlag.observeAsState(true)

                val editFlag by historyViewModel.editFlag.observeAsState(false)
                val trashList by historyViewModel.trashList.observeAsState(emptyList())

                AccountbookTheme {
                    Column {
                        HistoryMainFilterButton(
                            isIncomeChecked = incomeFilter,
                            isExpenditureChecked = expenditureFilter,
                            onIncomeButtonPressed = { historyViewModel.filteringIncomeData() },
                            onExpenditureButtonPressed = { historyViewModel.filteringExpenditureData() },
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
                            isActive = !editFlag
                        )
                        calendarData?.forEachIndexed { date, item ->
                            if (item.isNotEmpty())
                                AccountInfoPerDayItem(
                                    accountList = item,
                                    trashList = trashList,
                                    year = historyViewModel.year,
                                    month = historyViewModel.month,
                                    day = date,
                                    onItemClick = {
                                        if (!editFlag) changeFragment(FragmentTag, it)
                                        else historyViewModel.branchOffTrash(it)
                                    },
                                    onItemLongClick = { historyViewModel.branchOffTrash(it) }
                                )
                        }
                        if (emptyFlag) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Text(text = "내용이 없습니다", modifier = Modifier.align(Alignment.Center))
                            }
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
        floatingButton.setOnClickListener {
            changeFragment(FragmentTag)
        }
    }

    private fun observeData() {
        accountBookViewModel.totalHistoryList.observe(this.viewLifecycleOwner) { data ->
            historyViewModel.setTotalData(data)
        }
        accountBookViewModel.month.observe(this.viewLifecycleOwner) { month ->
            historyViewModel.year = accountBookViewModel.year.value ?: 2022
            historyViewModel.month = month
            val createTitle = "${historyViewModel.year}년 ${month}월"

            historyViewModel.historyTitle.value = createTitle
        }
        historyViewModel.editFlag.observe(this.viewLifecycleOwner) { floatingButton.isGone = it }
    }

    private fun changeFragment(tag: String, data: Serializable? = null) {
        val fragment = ManageHistoryFragment().apply {
            arguments = Bundle().apply {
                putSerializable(SharedData, data)
            }
        }
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .addToBackStack(tag)
            .commit()
    }

    companion object {
        const val FragmentTag = "NewHistory"
        const val SharedData = "SharedData"
    }
}