package com.woowa.accountbook.ui.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.R
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.ui.common.component.SubDivider
import com.woowa.accountbook.ui.theme.Purple200
import com.woowa.accountbook.ui.theme.Purple700
import com.woowa.accountbook.ui.theme.Typography

@Composable
fun SettingMainItem(
    title: String,
    itemList: List<Category>,
    categoryCardVisible: Boolean = false,
    onClickAddButton: () -> Unit = { },
    onClickItem: (Category) -> Unit = { },
    horizontalPadding: Dp = 16.dp,
) {
    LazyColumn(modifier = Modifier.padding(horizontal = horizontalPadding)) {
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = title, color = Purple200, style = Typography.subtitle1,
                    modifier = Modifier.padding(bottom = 10.dp, top = 24.dp)
                )
                SubDivider()
            }
        }
        items(itemList) { item ->
            SettingSubItem(
                item = item,
                categoryCardVisible = categoryCardVisible,
                onClickItem = onClickItem
            )
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
                    .clickable(true) { onClickAddButton() }
            ) {
                Text(
                    text = "$title 추가하기",
                    color = Purple700,
                    fontWeight = FontWeight.Bold,
                    style = Typography.subtitle2,
                )
                Icon(
                    painter = painterResource(id = R.drawable.ic_add_24dp),
                    contentDescription = null,
                    tint = Purple700,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
    }
}