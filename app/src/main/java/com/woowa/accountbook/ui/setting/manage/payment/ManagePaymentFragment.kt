package com.woowa.accountbook.ui.setting.manage.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.woowa.accountbook.R
import com.woowa.accountbook.domain.model.Payment
import com.woowa.accountbook.ui.AccountBookViewModel
import com.woowa.accountbook.ui.common.component.CommonButton
import com.woowa.accountbook.ui.common.component.ContentWithTitleItem
import com.woowa.accountbook.ui.common.component.SubAppBar
import com.woowa.accountbook.ui.common.component.TextFieldWithHint
import com.woowa.accountbook.ui.setting.SettingFragment
import com.woowa.accountbook.ui.theme.AccountbookTheme
import com.woowa.accountbook.ui.theme.Purple200
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManagePaymentFragment : Fragment() {

    private val managePaymentViewModel by viewModels<ManagePaymentViewModel>()
    private val accountBookViewModel: AccountBookViewModel by activityViewModels()

    private val oldPayment: Payment? by lazy {
        val payment = arguments?.getSerializable(SettingFragment.SharedData)
        if (payment != null) payment as Payment else null
    }

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_new_category, container, false)
        val editFlag = oldPayment != null

        rootView.findViewById<ComposeView>(R.id.cv_tool_bar).apply {
            setContent {
                AccountbookTheme {
                    SubAppBar(
                        title = "결제 수단 " + if (editFlag) "변경하기" else "추가하기",
                        onBackIconPressed = { parentFragmentManager.popBackStack() }
                    )
                }
            }
        }
        rootView.findViewById<ComposeView>(R.id.cv_new_content).apply {
            setContent {
                val name by managePaymentViewModel.paymentName.observeAsState("")
                val buttonActive by managePaymentViewModel.buttonEnabled.observeAsState(false)

                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.align(Alignment.TopCenter)) {
                        ContentWithTitleItem(title = "이름") {
                            TextFieldWithHint(
                                name,
                                onValueChange = { managePaymentViewModel.setPaymentName(it) },
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
                    ) {
                        CommonButton(text = "등록하기", isActive = buttonActive) {
                            if (editFlag) managePaymentViewModel.updatePayment(oldPaymentId = oldPayment!!.id)
                            else managePaymentViewModel.addPayment()
                        }
                    }
                }
            }
            return rootView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        oldPayment?.let {
            managePaymentViewModel.setPaymentName(it.name)
        }
        managePaymentViewModel.manageResult.observe(this@ManagePaymentFragment.viewLifecycleOwner) {
            if (it) {
                accountBookViewModel.fetchPaymentList()
                parentFragmentManager.popBackStack()
            } else {
                Toast.makeText(this.requireContext(), "문제가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}