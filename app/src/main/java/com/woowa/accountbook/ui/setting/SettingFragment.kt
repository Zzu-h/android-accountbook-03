package com.woowa.accountbook.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.woowa.accountbook.R
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.ui.common.component.MainAppBar
import com.woowa.accountbook.ui.common.component.MainDivider
import com.woowa.accountbook.ui.setting.component.SettingMainItem
import com.woowa.accountbook.ui.theme.AccountbookTheme

//
class SettingFragment : Fragment() {

    private val settingViewModel: SettingViewModel by viewModels()

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_setting, container, false)

        rootView.findViewById<ComposeView>(R.id.cv_tool_bar).apply {
            setContent {
                AccountbookTheme() {
                    MainAppBar(
                        title = "설정",
                        buttonVisible = false,
                    )
                }
            }
        }
        rootView.findViewById<ComposeView>(R.id.cv_setting_content).apply {
            setContent {
                val calendarData by settingViewModel.historyCalendar.observeAsState()

                AccountbookTheme {
                    Column {
                        SettingMainItem(
                            title = TagPayment, itemList = listOf(
                                Category(),
                                Category()
                            ), categoryCardVisible = false
                        )
                        MainDivider()
                        SettingMainItem(
                            title = TagExpenditure, itemList = listOf(
                                Category(),
                                Category()
                            )
                        )
                        MainDivider()
                        SettingMainItem(
                            title = TagIncome, itemList = listOf(
                                Category(),
                                Category()
                            )
                        )
                        MainDivider()
                    }
                }
            }
            return rootView
        }
    }

    companion object {
        const val TagPayment = "결제수단"
        const val TagExpenditure = "지출 카테고리"
        const val TagIncome = "수입 카테고리"
    }
}