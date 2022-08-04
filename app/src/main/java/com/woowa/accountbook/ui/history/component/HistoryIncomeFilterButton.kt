package com.woowa.accountbook.ui.history.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.R
import com.woowa.accountbook.ui.theme.AccountbookTheme
import com.woowa.accountbook.ui.theme.Purple200
import com.woowa.accountbook.utils.StringUtil

@Composable
fun HistoryIncomeFilterButton(
    onButtonPressed: () -> Unit = { },
    totPrice: Int = 0,
    isChecked: Boolean,
    isActive: Boolean,
    itemVisible: Boolean = true,
) {
    val buttonColor = ButtonDefaults.buttonColors(
        backgroundColor = if (isChecked) MaterialTheme.colors.primary else MaterialTheme.colors.primaryVariant,
        disabledBackgroundColor = Purple200,
        contentColor = Color.White,
        disabledContentColor = Color.White,
    )
    val modifiers = Modifier
        .fillMaxWidth(0.5f)

    Button(
        onClick = onButtonPressed,
        modifier = modifiers,
        enabled = isActive,
        colors = buttonColor,
        shape = RoundedCornerShape(topStartPercent = 35, bottomStartPercent = 35)
    ) {
        if (itemVisible)
            Image(
                painter = painterResource(id = if (isChecked) R.drawable.ic_chek_box_checked_12dp else R.drawable.ic_chek_box_unchecked_12dp),
                contentDescription = null,
                modifier = Modifier
                    .size(12.dp)
            )
        Text(text = "  수입  ")
        if (itemVisible)
            Text(text = StringUtil.getMoneyFormatString(totPrice))
    }
}

@Preview
@Composable
fun HistoryIncomeFilterButtonPreview() {
    AccountbookTheme {
        HistoryIncomeFilterButton(isChecked = true, isActive = true)
    }
}