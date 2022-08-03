package com.woowa.accountbook.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
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
        //CategoryCard(Category())
    }
}

@Preview
@Composable
fun AccountInfoPreview() {
    AccountbookTheme {
        /* AccountInfoItem(
             account = HistoryDto(1234, "hi", "content", type = TypeFilter.INCOME).toAccount(),
         )*/
    }
}

@Preview
@Composable
fun AccountInfoPerDayItemPreview() {
    AccountbookTheme {
        AccountInfoPerDayItem(
            accountList = listOf(
                /*HistoryDto(
                    1234,
                    "hi",
                    "content",
                    type = TypeFilter.INCOME
                ).toAccount(),
                HistoryDto(
                    1234,
                    "hi",
                    "content",
                    type = TypeFilter.INCOME
                ).toAccount()*/
            ),
            year = 2022,
            month = 7,
            day = 29
        )
    }
}

@Preview
@Composable
fun ContentWithTitleItemPreview() {
    AccountbookTheme {
        ContentWithTitleItem(title = "Test")
    }
}

@Preview
@Composable
fun TextFieldWithHintPreview() {
    AccountbookTheme {
        TextFieldWithHint(
            "1234",
            onValueChange = { str -> },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(
                color = MaterialTheme.colors.primary,
                fontWeight = FontWeight.Bold
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            keyboardActions = KeyboardActions(),
        )
    }
}
