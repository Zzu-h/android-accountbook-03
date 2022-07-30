package com.woowa.accountbook.ui.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.ui.common.component.CategoryCard
import com.woowa.accountbook.ui.common.component.SubDivider
import com.woowa.accountbook.ui.theme.Purple700
import com.woowa.accountbook.ui.theme.Typography

@Composable
fun SettingSubItem(
    item: Category,
    categoryCardVisible: Boolean = true,
    onClickItem: (Category) -> Unit = { },
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(true) { onClickItem(item) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
        ) {
            Text(
                text = item.name,
                color = Purple700,
                style = Typography.subtitle2,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterStart)
            )
            if (categoryCardVisible)
                CategoryCard(category = item, modifier = Modifier.align(Alignment.CenterEnd))
        }

        SubDivider()
    }
}