package com.woowa.accountbook.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.woowa.accountbook.data.dto.DBAccountDto
import com.woowa.accountbook.data.dto.toAccount
import com.woowa.accountbook.domain.model.AccountType
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
fun CategoryCardPreview() {
    AccountbookTheme {
        CategoryCard(Category())
    }
}

@Preview
@Composable
fun AccountInfoPreview() {
    AccountbookTheme {
        AccountInfoItem(
            isExpenditure = false,
            account = DBAccountDto(1234, "hi", "content", type = AccountType.INCOME).toAccount(),
        )
    }
}

@Preview
@Composable
fun AccountInfoPerDayItemPreview() {
    AccountbookTheme {
        AccountInfoPerDayItem(
            accountList = listOf(
                DBAccountDto(
                    1234,
                    "hi",
                    "content",
                    type = AccountType.INCOME
                ).toAccount(),
                DBAccountDto(
                    1234,
                    "hi",
                    "content",
                    type = AccountType.INCOME
                ).toAccount()
            ),
            year = 2022,
            month = 7,
            day = 29
        )
    }
}