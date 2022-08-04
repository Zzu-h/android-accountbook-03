package com.woowa.accountbook.ui.common.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woowa.accountbook.R
import com.woowa.accountbook.ui.theme.AccountbookTheme

@Composable
fun MainAppBar(
    modifier: Modifier = Modifier,
    onPrevIconPressed: () -> Unit = { },
    onNextIconPressed: () -> Unit = { },
    onTitlePressed: () -> Unit = { },
    title: String,
    buttonVisible: Boolean = true,
) {
    val height = 56.dp
    val contentColor = MaterialTheme.colors.primary

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {

        if (buttonVisible)
            Icon(
                painter = painterResource(id = R.drawable.ic_prev_24dp),
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable(true, onClick = onPrevIconPressed)
                    .padding(16.dp),
            )
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.Center)
                .clickable(true, onClick = onTitlePressed),
            color = contentColor,
            fontSize = 18.sp
        )
        if (buttonVisible)
            Icon(
                painter = painterResource(id = R.drawable.ic_next_24dp),
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable(true, onClick = onNextIconPressed)
                    .padding(16.dp)
            )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            color = contentColor,
            thickness = 1.dp,
        )
    }
}

@Preview
@Composable
fun MainAppBarPreview() {
    AccountbookTheme {
        MainAppBar(title = "Preview!")
    }
}