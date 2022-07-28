package com.woowa.accountbook.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.woowa.accountbook.ui.common.component.MainAppBar
import com.woowa.accountbook.ui.theme.AccountbookTheme

@Preview
@Composable
fun MainAppBarPreview() {
    AccountbookTheme {
        MainAppBar(title = "Preview!")
    }
}