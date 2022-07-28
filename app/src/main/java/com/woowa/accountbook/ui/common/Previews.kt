package com.woowa.accountbook.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.woowa.accountbook.domain.model.Category
import com.woowa.accountbook.ui.common.component.*
import com.woowa.accountbook.ui.theme.AccountbookTheme

@Preview
@Composable
fun MainAppBarPreview() {
    AccountbookTheme {
        MainAppBar(title = "Preview!")
    }
}

@Preview
@Composable
fun SubAppBarPreview() {
    AccountbookTheme {
        SubAppBar(title = "Preview!")
    }
}

@Preview
@Composable
fun CommonButtonPreview() {
    AccountbookTheme {
        CommonButton(text = "Preview!")
    }
}

@Preview
@Composable
fun CategoryCardPreview(){
    AccountbookTheme {
        CategoryCard(Category())
    }
}