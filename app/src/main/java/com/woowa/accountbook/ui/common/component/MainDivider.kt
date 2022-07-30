package com.woowa.accountbook.ui.common.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.ui.theme.Purple700

@Composable
fun MainDivider(
    thickness: Dp = 1.dp,
) {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(thickness),
        color = Purple700,
    )
}