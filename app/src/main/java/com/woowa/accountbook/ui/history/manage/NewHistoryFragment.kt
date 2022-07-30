package com.woowa.accountbook.ui.history.manage

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.woowa.accountbook.R
import com.woowa.accountbook.ui.AccountBookViewModel
import com.woowa.accountbook.ui.common.component.CommonButton
import com.woowa.accountbook.ui.common.component.ContentWithTitleItem
import com.woowa.accountbook.ui.common.component.SubAppBar
import com.woowa.accountbook.ui.common.component.TextFieldWithHint
import com.woowa.accountbook.ui.history.HistoryViewModel
import com.woowa.accountbook.ui.history.component.HistoryMainFilterButton
import com.woowa.accountbook.ui.history.manage.component.DropDownComponent
import com.woowa.accountbook.ui.theme.AccountbookTheme
import com.woowa.accountbook.ui.theme.Purple200
import com.woowa.accountbook.utils.DateUtil


class NewHistoryFragment : Fragment() {

    private val accountBookViewModel: AccountBookViewModel by activityViewModels()
    private val historyViewModel: HistoryViewModel by viewModels()

    private lateinit var cvAppBar: ComposeView
    private lateinit var cvContent: ComposeView
    private lateinit var datePickerDialog: DatePickerDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        datePickerDialog = DatePickerDialog(
            requireContext(),
            null,
            DateUtil.currentYear,
            DateUtil.currentMonth,
            DateUtil.currentDay
        )

        val rootView: View = inflater.inflate(R.layout.fragment_new_history, container, false)
        cvAppBar = rootView.findViewById(R.id.cv_tool_bar)
        cvContent = rootView.findViewById(R.id.cv_new_history_content)

        drawView()
        return rootView
    }

    private fun drawView() {
        cvAppBar.setContent {
            val title by historyViewModel.historyTitle.observeAsState("hihi")
            AccountbookTheme() {
                SubAppBar(
                    title = title,
                    onBackIconPressed = { parentFragmentManager.popBackStack() }
                )
            }
        }
        cvContent.setContent {
            val filter = false
            AccountbookTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.align(Alignment.TopCenter)) {
                        HistoryMainFilterButton(
                            isIncomeChecked = filter,
                            isExpenditureChecked = filter.not(),
                            onIncomeButtonPressed = {},
                            onExpenditureButtonPressed = {},
                            itemVisible = false,
                            modifier = Modifier.padding(16.dp)
                        )
                        ContentWithTitleItem(title = "일자") {
                            Text(
                                DateUtil.getDateToString(),
                                modifier = Modifier.clickable(true) { datePickerDialog.show() },
                                color = MaterialTheme.colors.primary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        ContentWithTitleItem(title = "금액") {
                            TextFieldWithHint(
                                "1234",
                                onValueChange = { str -> Log.d("Tester", str) },
                                singleLine = true,
                                modifier = Modifier.fillMaxWidth(),
                                textStyle = TextStyle(
                                    color = MaterialTheme.colors.primary,
                                    fontWeight = FontWeight.Bold
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                                hint = {
                                    Text(
                                        text = "입력하세요",
                                        fontWeight = FontWeight.Bold,
                                        color = Purple200
                                    )
                                }
                            )
                        }
                        if (filter.not()) {
                            ContentWithTitleItem(title = "결제 수단") { DropDownComponent() }
                        }
                        ContentWithTitleItem(title = "분류") { DropDownComponent() }
                        ContentWithTitleItem(title = "내용") {
                            TextFieldWithHint(
                                "1234",
                                onValueChange = { str -> Log.d("Tester", str) },
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
                                        color = Purple200
                                    )
                                }
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(vertical = 40.dp, horizontal = 16.dp)
                    ) { CommonButton(text = "등록하기") }
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
        buttonSetting()
    }

    private fun buttonSetting() {

    }

    private fun observeData() {

    }
}