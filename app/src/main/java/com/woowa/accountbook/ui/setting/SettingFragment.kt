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
import com.woowa.accountbook.domain.model.AccountType
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.ui.common.component.MainAppBar
import com.woowa.accountbook.ui.common.component.MainDivider
import com.woowa.accountbook.ui.setting.component.SettingMainItem
import com.woowa.accountbook.ui.setting.new.NewCategoryFragment
import com.woowa.accountbook.ui.setting.new.NewPaymentFragment
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
                            title = "결제수단", itemList = listOf(
                                Category(),
                                Category()
                            ), categoryCardVisible = false,
                            onClickAddButton = { changeFragment("") }
                        )
                        MainDivider()
                        SettingMainItem(
                            title = "수입 카테고리", itemList = listOf(
                                Category(),
                                Category()
                            ),
                            onClickAddButton = { changeFragment(AccountType.INCOME) }
                        )
                        MainDivider()
                        SettingMainItem(
                            title = "지출 카테고리", itemList = listOf(
                                Category(),
                                Category()
                            ),
                            onClickAddButton = { changeFragment(AccountType.EXPENDITURE) }
                        )
                        MainDivider()
                    }
                }
            }
            return rootView
        }
    }

    private fun changeFragment(tag: String) {
        val fragment = when (tag) {
            AccountType.INCOME, AccountType.EXPENDITURE -> NewCategoryFragment()
            else -> NewPaymentFragment()
        }.apply {
            arguments = Bundle().apply {
                putString("TAG", tag)
            }
        }
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .addToBackStack(tag)
            .commit()
    }
}