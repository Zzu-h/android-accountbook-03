package com.woowa.accountbook.ui.setting.new

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.woowa.accountbook.R
import com.woowa.accountbook.domain.model.AccountType
import com.woowa.accountbook.ui.common.component.*
import com.woowa.accountbook.ui.setting.new.component.ColorPaletteComponent
import com.woowa.accountbook.ui.theme.*

class NewCategoryFragment : Fragment() {

    private val categoryType by lazy {
        // TODO 카테고리 명을 받아올 예정
        AccountType.INCOME
    }

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_new_category, container, false)

        rootView.findViewById<ComposeView>(R.id.cv_tool_bar).apply {
            setContent { AccountbookTheme { SubAppBar(title = "카테고리 추가") } }
        }
        rootView.findViewById<ComposeView>(R.id.cv_setting_content).apply {
            setContent {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.align(Alignment.TopCenter)) {
                        ContentWithTitleItem(title = "이름") {
                            TextFieldWithHint(
                                "",
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
                        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                            Text(
                                text = "색상",
                                modifier = Modifier.padding(
                                    top = 24.dp,
                                    bottom = 10.dp
                                ),
                                color = MaterialTheme.colors.primaryVariant
                            )
                            SubDivider()
                        }
                        ColorPaletteComponent(
                            if (categoryType == AccountType.INCOME) income else expenditure,
                            modifier = Modifier.padding(16.dp)
                        ) { color ->

                        }
                        MainDivider()
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(vertical = 40.dp, horizontal = 16.dp)
                    ) { CommonButton(text = "등록하기") }
                }
            }
            return rootView
        }
    }

    companion object ColorList {
        val expenditure = listOf(
            Blue1,
            Blue2,
            Blue3,
            Blue4,
            Blue5,
            Green1,
            Green2,
            Green3,
            Green4,
            Green5,
            Purple1,
            Purple2,
            Purple3,
            Purple4,
            Purple5,
            Pink1,
            Pink2,
            Pink3,
            Pink4,
            Pink5,
        )
        val income = listOf(
            Olive1,
            Olive2,
            Olive3,
            Olive4,
            Olive5,
            Yellow1,
            Yellow2,
            Yellow3,
            Yellow4,
            Yellow5
        )
    }
}