package com.woowa.accountbook.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.woowa.accountbook.R
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.domain.model.Payment
import com.woowa.accountbook.ui.AccountBookViewModel
import com.woowa.accountbook.ui.common.component.MainAppBar
import com.woowa.accountbook.ui.common.component.MainDivider
import com.woowa.accountbook.ui.common.component.SubDivider
import com.woowa.accountbook.ui.setting.component.SettingContentItem
import com.woowa.accountbook.ui.setting.component.SettingMainItem
import com.woowa.accountbook.ui.setting.manage.category.ManageCategoryFragment
import com.woowa.accountbook.ui.setting.manage.payment.ManagePaymentFragment
import com.woowa.accountbook.ui.theme.AccountbookTheme
import com.woowa.accountbook.ui.theme.Purple200
import com.woowa.accountbook.ui.theme.Typography
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
                    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                        item {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "결제수단", color = Purple200, style = Typography.subtitle1,
                                    modifier = Modifier.padding(bottom = 10.dp, top = 24.dp)
                                )
                                SubDivider()
                            }
                        }
                        items(paymentList.map {
                            Category(
                                it.id,
                                it.name,
                                Purple200,
                                TypeFilter.PAYMENT
                            )
                        }) { item ->
                            SettingContentItem(
                                item = item,
                                categoryCardVisible = false,
                                onClickItem = {
                                    changeFragment(
                                        TypeFilter.PAYMENT,
                                        Payment(it.id, it.title)
                                    )
                                }
                            )
                        }
                        item {
                            SettingMainItem("결제수단 추가하기") { changeFragment(TypeFilter.PAYMENT) }
                            MainDivider()
                        }
                        item {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "수입 카테고리",
                                    color = Purple200,
                                    style = Typography.subtitle1,
                                    modifier = Modifier.padding(bottom = 10.dp, top = 24.dp)
                                )
                                SubDivider()
                            }
                        }
                        items(incomeList) { item ->
                            SettingContentItem(
                                item = item,
                                categoryCardVisible = false,
                                onClickItem = { changeFragment(TypeFilter.INCOME, it) }
                            )
                        }
                        item {
                            SettingMainItem("수입 카테고리 추가하기") { changeFragment(TypeFilter.INCOME) }
                            MainDivider()
                        }
                        item {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "지출 카테고리",
                                    color = Purple200,
                                    style = Typography.subtitle1,
                                    modifier = Modifier.padding(bottom = 10.dp, top = 24.dp)
                                )
                                SubDivider()
                            }
                        }
                        items(expenditureList) { item ->
                            SettingContentItem(
                                item = item,
                                categoryCardVisible = false,
                                onClickItem = { changeFragment(TypeFilter.EXPENDITURE, it) },
                            )
                        }
                        item {
                            SettingMainItem("지출 카테고리 추가하기") { changeFragment(TypeFilter.EXPENDITURE) }
                            MainDivider()
                        }
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
            else -> ManagePaymentFragment()
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