package com.woowa.accountbook.ui.common.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.ui.theme.AccountbookTheme

@Composable
fun ContentWithTitleItem(
    title: String,
    modifier: Modifier = Modifier.padding(horizontal = 16.dp),
    titleColor: Color = MaterialTheme.colors.primary,
    titleFontSize: TextUnit = TextUnit.Unspecified,
    titleFontStyle: FontStyle? = null,
    titleFontWeight: FontWeight? = null,
    content: @Composable () -> Unit = { Text(text = "Input the Component") }
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.padding(vertical = 10.dp)) {
            Text(
                title, modifier = Modifier.defaultMinSize(minWidth = 80.dp),
                color = titleColor,
                fontSize = titleFontSize,
                fontStyle = titleFontStyle,
                fontWeight = titleFontWeight,
            )
            content()
        }
        SubDivider()
    }
}

@Preview
@Composable
fun ContentWithTitleItemPreview() {
    AccountbookTheme {
        ContentWithTitleItem(title = "Test")
    }
}