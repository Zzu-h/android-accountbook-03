package com.woowa.accountbook.ui.calendar.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowa.accountbook.ui.theme.*
import com.woowa.accountbook.utils.StringUtil

@ExperimentalFoundationApi
@Composable
fun CustomCalendarItem(
    day: Int,
    income: Int = 0,
    expenditure: Int = 0,
    isVisible: Boolean = true,
    isToday: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = if (isToday) Color.White else MaterialTheme.colors.background
    val textColor = if (isVisible) Purple700 else Purple200
    val textSize = (9).sp

    Box(
        modifier = modifier
            .defaultMinSize(minHeight = 60.dp)
            .height(70.dp)
            .border(width = (0.3).dp, color = Purple200)
            .background(backgroundColor)
    ) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            Column(modifier = Modifier.align(Alignment.TopStart)) {
                if (income != 0) Text(
                    text = StringUtil.getMoneyFormatString(income),
                    color = Teal200,
                    fontSize = textSize
                )
                if (expenditure != 0) Text(
                    text = "-${StringUtil.getMoneyFormatString(expenditure)}",
                    color = Red,
                    fontSize = textSize
                )
                if (income != 0 || expenditure != 0) Text(
                    text = (if (income < expenditure) "-" else "") + StringUtil.getMoneyFormatString(
                        (income - expenditure)
                    ),
                    color = textColor,
                    fontSize = textSize
                )
            }
            Text(
                text = day.toString(),
                color = textColor,
                modifier = Modifier.align(Alignment.BottomEnd),
                fontSize = textSize
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(widthDp = 40)
@Composable
fun CustomCalendarItemPreview() {
    AccountbookTheme {
        CustomCalendarItem(23, 234, 567)
    }
}