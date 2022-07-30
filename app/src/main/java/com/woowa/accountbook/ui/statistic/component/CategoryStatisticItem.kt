package com.woowa.accountbook.ui.statistic.component

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.domain.model.CategoryStatisticDto
import com.woowa.accountbook.ui.common.component.CategoryCard
import com.woowa.accountbook.ui.common.component.SubDivider
import com.woowa.accountbook.utils.StringUtil

@Composable
fun CategoryStatisticItem(
    item: CategoryStatisticDto,
    verticalPadding: Dp = 10.dp,
    horizontalPadding: Dp = 16.dp,
) {
    Column(Modifier.padding(horizontal = horizontalPadding)) {
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
