package com.woowa.accountbook.ui.history

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.woowa.accountbook.ui.history.component.HistoryExpenditureFilterButton
import com.woowa.accountbook.ui.history.component.HistoryIncomeFilterButton
import com.woowa.accountbook.ui.history.component.HistoryMainFilterButton
import com.woowa.accountbook.ui.theme.AccountbookTheme

@Preview
@Composable
fun HistoryIncomeFilterButtonPreview() {
    AccountbookTheme {
        HistoryIncomeFilterButton(isChecked = true, isActive = true)
    }
}

@Preview
@Composable
fun HistoryExpenditureFilterButtonPreview() {
    AccountbookTheme {
        HistoryExpenditureFilterButton(isChecked = true, isActive = true)
    }
}

@Preview
@Composable
fun HistoryMainFilterButtonPreview() {
    AccountbookTheme {
        HistoryMainFilterButton()
    }
}