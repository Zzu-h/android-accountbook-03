package com.woowa.accountbook.ui.common.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.domain.model.Account
import com.woowa.accountbook.domain.model.AccountType
import com.woowa.accountbook.utils.DateUtil
import com.woowa.accountbook.utils.StringUtil

@Composable
fun AccountInfoPerDayItem(
    accountList: List<Account>,
    year: Int,
    month: Int,
    day: Int,
    modifier: Modifier = Modifier,
) {
    val titleColor = MaterialTheme.colors.primaryVariant
    var totIncome = 0
    var totExpenditure = 0

    accountList.forEach { if (it.type == AccountType.INCOME) totIncome += it.price else totExpenditure += it.price }

    val dateText = "${month}월 ${day}일 ${DateUtil.getDayOfWeek(year, month, day)}"
    val totIncomeText = "수입 ${StringUtil.getPriceToString(totIncome, false)}"
    val totExpenditureText = "지출 ${StringUtil.getPriceToString(totExpenditure, false)}"

    Column(modifier = modifier.padding(top = 24.dp)) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(dateText, modifier = Modifier.align(Alignment.BottomStart), color = titleColor)
                Row(modifier = Modifier.align(Alignment.BottomEnd)) {
                    if (totIncome > 0) Text(totIncomeText, color = titleColor)
                    if (totExpenditure > 0) Text(totExpenditureText, color = titleColor)
                }
            }

            LazyColumn(
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                item(accountList.size) {
                    accountList.forEach {
                        AccountInfoItem(it.type, it)
                    }
                }
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = MaterialTheme.colors.primary,
        )
    }
}