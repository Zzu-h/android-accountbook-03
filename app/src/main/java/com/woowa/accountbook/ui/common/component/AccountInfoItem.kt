package com.woowa.accountbook.ui.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.R
import com.woowa.accountbook.domain.model.Account
import com.woowa.accountbook.ui.theme.Red
import com.woowa.accountbook.ui.theme.Teal200
import com.woowa.accountbook.utils.StringUtil
import com.woowa.accountbook.utils.TypeFilter

@Composable
fun AccountInfoItem(
    account: Account,
    isSelected: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val priceColor = if (account.type == TypeFilter.EXPENDITURE) Red else Teal200
    val contentColor = MaterialTheme.colors.primary
    val backgroundColor = if (isSelected) Color.White else Color.Transparent

    val content: String = account.content ?: " "
    val payment = account.payment
    val price = StringUtil.getPriceToString(account.price, account.type == TypeFilter.EXPENDITURE)
    val category = account.category

    Box(modifier = modifier.background(color = backgroundColor)) {
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .height(1.dp),
            color = MaterialTheme.colors.primaryVariant,
        )
        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(vertical = 8.dp)
        ) {
            if (isSelected)
                Image(
                    painter = painterResource(id = R.drawable.ic_selected_24dp),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(16.dp)
                )
            Column() {
                CategoryCard(
                    category = category,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )
                Text(
                    text = content,
                    color = contentColor
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = payment, modifier = Modifier
                    .padding(bottom = 8.dp)
                    .align(Alignment.End),
                color = contentColor,
                textAlign = TextAlign.End
            )
            Text(
                text = price,
                color = priceColor
            )
        }

    }
}