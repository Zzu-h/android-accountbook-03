package com.woowa.accountbook.ui.setting.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowa.accountbook.R
import com.woowa.accountbook.ui.theme.Purple700

@Composable
fun SettingMainItem(
    title: String,
    onClickAddButton: () -> Unit = { },
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable(true) { onClickAddButton() }
    ) {
        Text(
            text = title,
            color = Purple700,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_add_24dp),
            contentDescription = null,
            tint = Purple700,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}