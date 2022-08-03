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
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.woowa.accountbook.R
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.domain.model.Payment
import com.woowa.accountbook.ui.AccountBookViewModel
import com.woowa.accountbook.ui.common.component.MainAppBar
import com.woowa.accountbook.ui.common.component.MainDivider
import com.woowa.accountbook.ui.setting.component.SettingMainItem
import com.woowa.accountbook.ui.setting.manage.category.ManageCategoryFragment
import com.woowa.accountbook.ui.setting.manage.payment.NewPaymentFragment
import com.woowa.accountbook.ui.theme.AccountbookTheme
import com.woowa.accountbook.ui.theme.Purple200
import com.woowa.accountbook.utils.TypeFilter
import java.io.Serializable

class SettingFragment : Fragment() {

    private val accountBookViewModel: AccountBookViewModel by activityViewModels()
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
                val incomeList by settingViewModel.incomeCategoryList.observeAsState(emptyList())
                val expenditureList by settingViewModel.expenditureCategoryList.observeAsState(
                    emptyList()
                )
                val paymentList by settingViewModel.paymentList.observeAsState(emptyList())

                AccountbookTheme {
                    Column {
                        SettingMainItem(
                            title = "결제수단", itemList = paymentList.map {
                                Category(it.id, it.name, Purple200, TypeFilter.PAYMENT)
                            }, categoryCardVisible = false,
                            onClickItem = {
                                changeFragment(
                                    TypeFilter.PAYMENT,
                                    Payment(it.id, it.title)
                                )
                            },
                            onClickAddButton = { changeFragment(TypeFilter.PAYMENT) }
                        )
                        MainDivider()
                        SettingMainItem(
                            title = "수입 카테고리", itemList = incomeList,
                            onClickItem = { changeFragment(TypeFilter.INCOME, it) },
                            onClickAddButton = { changeFragment(TypeFilter.INCOME) }
                        )
                        MainDivider()
                        SettingMainItem(
                            title = "지출 카테고리", itemList = expenditureList,
                            onClickItem = { changeFragment(TypeFilter.EXPENDITURE, it) },
                            onClickAddButton = { changeFragment(TypeFilter.EXPENDITURE) }
                        )
                        MainDivider()
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
        accountBookViewModel.totalCategoryList.observe(this.viewLifecycleOwner) {
            settingViewModel.setCategoryList(it)
        }
        accountBookViewModel.totalPaymentList.observe(this.viewLifecycleOwner) {
            settingViewModel.setPaymentList(it)
        }
    }

    private fun changeFragment(tag: String, data: Serializable? = null) {
        val fragment = when (tag) {
            TypeFilter.INCOME, TypeFilter.EXPENDITURE -> ManageCategoryFragment()
            else -> NewPaymentFragment()
        }.apply {
            arguments = Bundle().apply {
                putString(FilterTag, tag)
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
        const val FilterTag = "FilterTag"
        const val SharedData = "SharedData"
    }
}