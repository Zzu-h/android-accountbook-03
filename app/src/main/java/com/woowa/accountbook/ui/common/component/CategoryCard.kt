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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.ui.theme.Blue1
import com.woowa.accountbook.ui.theme.Typography

@Composable
fun CategoryCard(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Blue1,
) {
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