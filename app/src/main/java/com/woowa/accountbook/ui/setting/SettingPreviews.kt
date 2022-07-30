package com.woowa.accountbook.ui.setting

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.ui.setting.component.SettingMainItem
import com.woowa.accountbook.ui.theme.AccountbookTheme

@Composable
@Preview
fun SettingMainItemPreviews (){
    AccountbookTheme {
        SettingMainItem(
            "Test",
            listOf(Category(),Category())
        )
    }
}