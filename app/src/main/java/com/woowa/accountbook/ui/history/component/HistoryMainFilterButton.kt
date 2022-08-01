package com.woowa.accountbook.ui.history.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HistoryMainFilterButton(
    isIncomeChecked: Boolean = true,
    isExpenditureChecked: Boolean = true,
    isActive: Boolean = true,
    itemVisible: Boolean = true,
    onIncomeButtonPressed: () -> Unit = { },
    onExpenditureButtonPressed: () -> Unit = { },
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(40.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(35))
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(35)
            )
    ) {
        HistoryIncomeFilterButton(
            isChecked = isIncomeChecked,
            onButtonPressed = onIncomeButtonPressed,
            isActive = isActive,
            itemVisible = itemVisible
        )
        HistoryExpenditureFilterButton(
            isChecked = isExpenditureChecked,
            onButtonPressed = onExpenditureButtonPressed,
            isActive = isActive,
            itemVisible = itemVisible
        )
    }
}