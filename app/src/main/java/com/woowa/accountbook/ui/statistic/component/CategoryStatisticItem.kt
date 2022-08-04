package com.woowa.accountbook.ui.statistic.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.domain.model.CategoryStatistic
import com.woowa.accountbook.ui.common.component.CategoryCard
import com.woowa.accountbook.ui.common.component.SubDivider
import com.woowa.accountbook.ui.theme.AccountbookTheme
import com.woowa.accountbook.utils.StringUtil

@Composable
fun CategoryStatisticItem(
    item: CategoryStatistic,
    verticalPadding: Dp = 10.dp,
    horizontalPadding: Dp = 16.dp,
    onClick: (Category) -> Unit = {}
) {
    Column(
        Modifier
            .padding(horizontal = horizontalPadding)
            .clickable(true) { onClick(item.category) }) {
        Row(modifier = Modifier.padding(vertical = verticalPadding)) {
            CategoryCard(category = item.category)
            Text(
                text = StringUtil.getMoneyFormatString(item.totalPrice),
                modifier = Modifier.padding(horizontal = 8.dp),
                color = MaterialTheme.colors.primary
            )
            Text(
                text = StringUtil.getPercentageFormatString(item.ratio),
                modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            )
        }
        SubDivider()
    }
}

@Composable
@Preview
fun CategoryStatisticItemPreview() {
    AccountbookTheme {
        //CategoryStatisticItem(CategoryStatisticDto(Category(), 12345, 0.3f))
    }
}