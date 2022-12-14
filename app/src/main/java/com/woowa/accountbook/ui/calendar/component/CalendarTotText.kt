package com.woowa.accountbook.ui.calendar.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.ui.theme.AccountbookTheme
import com.woowa.accountbook.ui.theme.Purple200
import com.woowa.accountbook.ui.theme.Purple700
import com.woowa.accountbook.ui.theme.Teal200

@Composable
fun CalendarTotText(
    title: String,
    price: String,
    color: Color,
    modifier: Modifier = Modifier,
) {

    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = title, color = Purple700)
            Text(
                text = price,
                modifier = Modifier.fillMaxWidth(),
                color = color,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Purple200,
        )
    }
}

@Preview
@Composable
fun CalendarTotTextPreview() {
    AccountbookTheme {
        CalendarTotText("수입", "123122321", color = Teal200)
    }
}