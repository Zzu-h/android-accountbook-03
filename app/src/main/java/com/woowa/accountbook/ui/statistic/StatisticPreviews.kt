package com.woowa.accountbook.ui.statistic

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.domain.model.CategoryStatisticDto
import com.woowa.accountbook.ui.statistic.component.CategoryStatisticItem
import com.woowa.accountbook.ui.statistic.component.GraphComponent
import com.woowa.accountbook.ui.theme.AccountbookTheme

@Composable
@Preview
fun GraphComponentPreview() {
    AccountbookTheme {
        GraphComponent(
            listOf(
                CategoryStatisticDto(Category(), 12345, 0.3f),
                CategoryStatisticDto(Category(), 12345, 0.3f),
                CategoryStatisticDto(Category(), 12345, 0.4f)
            )
        )
    }
}

@Composable
@Preview
fun CategoryStatisticItemPreview() {
    AccountbookTheme {
        CategoryStatisticItem(CategoryStatisticDto(Category(), 12345, 0.3f))
    }
}