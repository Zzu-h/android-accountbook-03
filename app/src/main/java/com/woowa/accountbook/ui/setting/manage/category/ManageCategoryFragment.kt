package com.woowa.accountbook.ui.setting.manage.category

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
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.ui.AccountBookViewModel
import com.woowa.accountbook.ui.common.component.*
import com.woowa.accountbook.ui.setting.SettingFragment.Companion.FilterTag
import com.woowa.accountbook.ui.setting.SettingFragment.Companion.SharedData
import com.woowa.accountbook.ui.setting.manage.component.ColorPaletteComponent
import com.woowa.accountbook.ui.theme.*
import com.woowa.accountbook.utils.TypeFilter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ManageCategoryFragment : Fragment() {

    private val manageCategoryViewModel by viewModels<ManageCategoryViewModel>()
    private val accountBookViewModel: AccountBookViewModel by activityViewModels()

    private val categoryType by lazy { requireArguments().getString(FilterTag) }
    private val oldCategory: Category? by lazy {
        val category = arguments?.getSerializable(SharedData)
        if (category != null) category as Category else null
    }

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_new_category, container, false)
        val editFlag = oldCategory != null

        rootView.findViewById<ComposeView>(R.id.cv_tool_bar).apply {
            setContent { AccountbookTheme { SubAppBar(title = if (categoryType == TypeFilter.INCOME) "수입" else "지출" + " 카테고리 " + if (editFlag) "변경하기" else "추가") } }
        }
        rootView.findViewById<ComposeView>(R.id.cv_new_content).apply {
            setContent {
                val name by manageCategoryViewModel.categoryName.observeAsState("")
                val color by manageCategoryViewModel.categoryColor.observeAsState(if (categoryType == TypeFilter.INCOME) income.first() else expenditure.first())
                val buttonActive by manageCategoryViewModel.buttonEnabled.observeAsState(false)

                Box(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.align(Alignment.TopCenter)) {
                        ContentWithTitleItem(title = "이름") {
                            TextFieldWithHint(
                                name,
                                onValueChange = { manageCategoryViewModel.setCategoryName(it) },
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
                                color = Purple200
                            )
                            SubDivider()
                        }
                        ColorPaletteComponent(
                            if (categoryType == TypeFilter.INCOME) income else expenditure,
                            currentColor = color,
                            modifier = Modifier.padding(16.dp)
                        ) { manageCategoryViewModel.setCategoryColor(it) }
                        MainDivider()
                    }
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(vertical = 40.dp, horizontal = 16.dp)
                    ) {
                        CommonButton(text = "등록하기", isActive = buttonActive) {
                            if (editFlag) manageCategoryViewModel.updateCategory(
                                filter = categoryType!!,
                                oldCategoryId = oldCategory!!.id
                            )
                            else manageCategoryViewModel.addCategory(filter = categoryType!!)
                        }
                    }
                }
            }
            return rootView
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        oldCategory?.let {
            manageCategoryViewModel.setCategoryColor(it.color)
            manageCategoryViewModel.setCategoryName(it.title)
        }
        manageCategoryViewModel.manageResult.observe(this@ManageCategoryFragment.viewLifecycleOwner) {
            if (it) {
                accountBookViewModel.fetchCategoryList()
                parentFragmentManager.popBackStack()
            } else {
                Toast.makeText(this.requireContext(), "문제가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
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