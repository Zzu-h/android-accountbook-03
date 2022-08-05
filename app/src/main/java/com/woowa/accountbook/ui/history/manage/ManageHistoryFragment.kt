package com.woowa.accountbook.ui.history.manage

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.woowa.accountbook.R
import com.woowa.accountbook.domain.model.Date
import com.woowa.accountbook.domain.model.History
import com.woowa.accountbook.ui.AccountBookViewModel
import com.woowa.accountbook.ui.common.component.CommonButton
import com.woowa.accountbook.ui.common.component.ContentWithTitleItem
import com.woowa.accountbook.ui.common.component.SubAppBar
import com.woowa.accountbook.ui.common.component.TextFieldWithHint
import com.woowa.accountbook.ui.history.HistoryFragment.Companion.SharedData
import com.woowa.accountbook.ui.history.component.HistoryMainFilterButton
import com.woowa.accountbook.ui.history.manage.component.DropDownComponent
import com.woowa.accountbook.ui.setting.SettingFragment
import com.woowa.accountbook.ui.setting.manage.category.ManageCategoryFragment
import com.woowa.accountbook.ui.setting.manage.payment.ManagePaymentFragment
import com.woowa.accountbook.ui.theme.AccountbookTheme
import com.woowa.accountbook.ui.theme.Purple200
import com.woowa.accountbook.utils.DateUtil
import com.woowa.accountbook.utils.TypeFilter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageHistoryFragment : Fragment() {

    private val accountBookViewModel: AccountBookViewModel by activityViewModels()
    private val manageHistoryViewModel: ManageHistoryViewModel by viewModels()

    private lateinit var cvAppBar: ComposeView
    private lateinit var cvContent: ComposeView
    private lateinit var datePickerDialog: DatePickerDialog

    private val oldHistory: History? by lazy {
        val history = arguments?.getSerializable(SharedData)
        if (history != null) history as History else null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        DatePickerDialog(
            requireContext(),
            { _, y, m, d -> manageHistoryViewModel.setDate(Date(y, m, d)) },
            DateUtil.currentYear,
            DateUtil.currentMonth,
            DateUtil.currentDay
        ).also { datePickerDialog = it }

        val rootView: View = inflater.inflate(R.layout.fragment_new_history, container, false)
        cvAppBar = rootView.findViewById(R.id.cv_tool_bar)
        cvContent = rootView.findViewById(R.id.cv_new_history_content)

        drawView()
        return rootView
    }

    private fun drawView() {
        val editFlag = oldHistory != null
        cvAppBar.setContent {
            val title = if (!editFlag) "내역 등록" else
                (if (oldHistory!!.type == TypeFilter.INCOME) "수입" else "지출") + " 수정하기"

            AccountbookTheme() {
                SubAppBar(
                    title = title,
                    onBackIconPressed = { parentFragmentManager.popBackStack() }
                )
            }
        }
        cvContent.setContent {
            val filter by manageHistoryViewModel.filterType.observeAsState(TypeFilter.EXPENDITURE)
            val paymentList by manageHistoryViewModel.paymentList.observeAsState(emptyList())
            val categoryList by manageHistoryViewModel.categoryList.observeAsState(emptyList())

            val date by manageHistoryViewModel.date.observeAsState(DateUtil.invoke())
            val price by manageHistoryViewModel.price.observeAsState()
            val payment by manageHistoryViewModel.payment.observeAsState()
            val category by manageHistoryViewModel.category.observeAsState()

            val content by manageHistoryViewModel.content.observeAsState("")

            val buttonEnabled by manageHistoryViewModel.buttonEnabled.observeAsState(false)

            val fontSize = 14.sp

            AccountbookTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.align(Alignment.TopCenter)) {
                        HistoryMainFilterButton(
                            isIncomeChecked = filter == TypeFilter.INCOME,
                            isExpenditureChecked = filter == TypeFilter.EXPENDITURE,
                            onIncomeButtonPressed = { manageHistoryViewModel.setType(TypeFilter.INCOME) },
                            onExpenditureButtonPressed = { manageHistoryViewModel.setType(TypeFilter.EXPENDITURE) },
                            itemVisible = false,
                            isActive = !editFlag,
                            modifier = Modifier.padding(16.dp)
                        )
                        ContentWithTitleItem(title = "일자", titleFontSize = fontSize) {
                            Text(
                                DateUtil.getDateToString(date),
                                modifier = Modifier.clickable(true) { datePickerDialog.show() },
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold,
                                fontSize = fontSize
                            )
                        }
                        ContentWithTitleItem(title = "금액", titleFontSize = fontSize) {
                            TextFieldWithHint(
                                price ?: "",
                                onValueChange = { manageHistoryViewModel.setPrice(it.toPriceFormat()) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                textStyle = TextStyle(
                                    color = MaterialTheme.colors.primary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = fontSize
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                                cursorToLast = true,
                                hint = {
                                    Text(
                                        text = "입력하세요",
                                        fontWeight = FontWeight.Bold,
                                        color = Purple200,
                                        fontSize = fontSize
                                    )
                                }
                            )
                        }
                        if (filter == TypeFilter.EXPENDITURE) {
                            ContentWithTitleItem(title = "결제 수단", titleFontSize = fontSize) {
                                DropDownComponent(
                                    list = paymentList.map { it.name },
                                    selectItem = payment
                                ) {
                                    if (it == "ADD-TAG") changeFragment(TypeFilter.PAYMENT)
                                    else manageHistoryViewModel.setPayment(it)
                                }
                            }
                        }
                        ContentWithTitleItem(title = "분류", titleFontSize = fontSize) {
                            DropDownComponent(
                                list = categoryList.map { it.title },
                                selectItem = category
                            ) {
                                if (it == "ADD-TAG") changeFragment(manageHistoryViewModel.filterType.value!!)
                                else manageHistoryViewModel.setCategory(it)
                            }
                        }
                        ContentWithTitleItem(title = "내용", titleFontSize = fontSize) {
                            TextFieldWithHint(
                                content,
                                onValueChange = { str -> manageHistoryViewModel.setContent(str) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                textStyle = TextStyle(
                                    color = MaterialTheme.colors.primary,
                                    fontWeight = FontWeight.Bold
                                ),
                                hint = {
                                    Text(
                                        text = "입력하세요",
                                        fontWeight = FontWeight.Bold,
                                        color = Purple200,
                                        fontSize = fontSize
                                    )
                                }
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(vertical = 40.dp, horizontal = 16.dp)
                    ) {
                        CommonButton(
                            text = if (editFlag) "수정하기" else "등록하기",
                            isActive = buttonEnabled
                        ) {
                            if (editFlag) manageHistoryViewModel.updateHistory(oldHistoryId = oldHistory!!.id)
                            else manageHistoryViewModel.addHistory()
                        }
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        observeData()
        oldHistory?.let {
            manageHistoryViewModel.setType(it.type)
            manageHistoryViewModel.setDate(Date(it.year, it.month, it.day))
            manageHistoryViewModel.setCategory(it.category.title)
            manageHistoryViewModel.setContent(it.content ?: "")
            manageHistoryViewModel.setPrice(it.price.toString().toPriceFormat())
            manageHistoryViewModel.setPayment(it.payment.name)
        }
    }

    private fun changeFragment(tag: String) {
        val fragment = when (tag) {
            TypeFilter.INCOME, TypeFilter.EXPENDITURE -> ManageCategoryFragment()
            else -> ManagePaymentFragment()
        }.apply {
            arguments = Bundle().apply {
                putString(SettingFragment.FilterTag, tag)
            }
        }
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .addToBackStack(tag)
            .commit()
    }

    private fun observeData() {
        accountBookViewModel.totalPaymentList.observe(this@ManageHistoryFragment.viewLifecycleOwner) {
            manageHistoryViewModel.setPaymentList(it)
        }
        accountBookViewModel.totalCategoryList.observe(this@ManageHistoryFragment.viewLifecycleOwner) {
            manageHistoryViewModel.setCategoryList(it)
        }
        manageHistoryViewModel.manageResult.observe(this@ManageHistoryFragment.viewLifecycleOwner) {
            if (it) {
                accountBookViewModel.fetchHistoryList()
                parentFragmentManager.popBackStack()
            } else {
                Toast.makeText(this.requireContext(), "문제가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun String.toPriceFormat(): String {
        var ret = ""
        if (this.isBlank()) return ret
        val str = this.replace(",", "").reversed()
        val comaInterval = 3

        str.forEachIndexed { i, it ->
            ret += it
            if (i % comaInterval == 2 && i < str.lastIndex) ret += ','
        }
        return ret.reversed()
    }
}