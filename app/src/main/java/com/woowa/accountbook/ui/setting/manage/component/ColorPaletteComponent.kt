package com.woowa.accountbook.ui.setting.manage.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@Composable
fun ColorPaletteComponent(
    colors: List<Color>,
    currentColor: Color = colors.first(),
    modifier: Modifier = Modifier,
    onColorClick: (Color) -> Unit = {}
) {
    val colorSize = 35.dp

    LazyVerticalGrid(
        cells = GridCells.Adaptive(colorSize),
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy((5).dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = colors,
        ) {
            Box(
                modifier = Modifier
                    .defaultMinSize(minHeight = colorSize - (4).dp)
                    .padding(if (currentColor == it) 0.dp else 5.dp)
                    .background(it)
                    .clickable(true) { onColorClick(it) }
            )
        }
    }
}