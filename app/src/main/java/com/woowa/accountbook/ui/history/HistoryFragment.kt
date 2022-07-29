package com.woowa.accountbook.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.woowa.accountbook.R
import com.woowa.accountbook.ui.AccountBookViewModel
import com.woowa.accountbook.ui.common.component.MainAppBar
import com.woowa.accountbook.ui.theme.AccountbookTheme

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
                    )
                }
            }
        }
        rootView.findViewById<ComposeView>(R.id.cv_history_content).apply {
            setContent {
                //val calendarData by historyViewModel.historyCalendar.forEach { it.observeAsState() }
                LazyColumn {
                    for(day in 1..historyViewModel.maxDate){

                    }
                    item {
                        Text(text = "First item")
                    }

                    // Add 5 items
                    items(5) { index ->
                        Text(text = "Item: $index")
                    }

                    // Add another single item
                    item {
                        Text(text = "Last item")
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
            val year = accountBookViewModel.year.value ?: 0
            val createTitle = "${year}년 ${month}월"

            historyViewModel.historyTitle.value = createTitle
        }
        historyViewModel.historyCalendar.forEachIndexed { index, item ->
            item.observe(this.viewLifecycleOwner) { }
        }
    }
}