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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.R

@Composable
fun SubAppBar(
    modifier: Modifier = Modifier,
    onBackIconPressed: () -> Unit = { },
    onTrashIconPressed: () -> Unit = { },
    title: String,
    trashButtonActivate: Boolean = false,
) {
    val height = 56.dp
    val contentColor = MaterialTheme.colors.primary

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_back_24dp),
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable(true, onClick = onBackIconPressed)
                .padding(16.dp),
        )
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.Center),
            color = contentColor
        )
        if (trashButtonActivate)
            Icon(
                painter = painterResource(id = R.drawable.ic_trash_24dp),
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable(true, onClick = onTrashIconPressed)
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