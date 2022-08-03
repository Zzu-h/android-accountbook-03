package com.woowa.accountbook.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.domain.model.History
import com.woowa.accountbook.utils.DateUtil
import com.woowa.accountbook.utils.StringUtil
import com.woowa.accountbook.utils.TypeFilter

@Composable
fun HistoryHeaderItem(
    accountList: List<History>,
    year: Int,
    month: Int,
    day: Int,
) {
    val titleColor = MaterialTheme.colors.primaryVariant
    var totIncome = 0
    var totExpenditure = 0

    accountList.forEach { if (it.type == TypeFilter.INCOME) totIncome += it.price else totExpenditure += it.price }

    val dateText = "${month}월 ${day}일 ${DateUtil.getDayOfWeek(year, month, day)}"
    val totIncomeText = "수입  ${StringUtil.getPriceToString(totIncome)}"
    val totExpenditureText = "  지출  ${StringUtil.getPriceToString(totExpenditure)}"

    Column(modifier = Modifier.background(color = MaterialTheme.colors.background)) {
        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 24.dp, bottom = 8.dp)
                .fillMaxWidth()
        ) {
            Text(
                dateText,
                modifier = Modifier.align(Alignment.BottomStart),
                color = titleColor
            )
            Row(modifier = Modifier.align(Alignment.BottomEnd)) {
                if (totIncome > 0) Text(totIncomeText, color = titleColor)
                if (totExpenditure > 0) Text(totExpenditureText, color = titleColor)
            }
        }
    }
}