package com.woowa.accountbook.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.domain.model.Account
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.ui.theme.AccountbookTheme
import com.woowa.accountbook.ui.theme.Typography
import java.util.*

@Composable
fun CategoryCard(
    category: Category,
    modifier: Modifier = Modifier,
) {
    val text = category.name
    val color = category.color

    Box(
        modifier = modifier
            .background(
                color = color,
                shape = RoundedCornerShape(100)
            )
            .height(18.dp)
            .width(56.dp),
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.Center),
            style = Typography.caption,
        )
    }
}

@Preview
@Composable
fun AccountInfoItemPreview(){
    AccountbookTheme {
        AccountInfoItem(false, Account(
            content = "Test",
            payment = "Testing",
            price = 10020,
            date = Date()
        )
        )
    }
}