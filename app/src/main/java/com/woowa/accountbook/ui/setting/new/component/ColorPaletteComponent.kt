package com.woowa.accountbook.ui.setting.new.component

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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalFoundationApi
@Composable
fun ColorPaletteComponent(
    colors: List<Color>,
    modifier: Modifier = Modifier,
    onColorChangeListener: (Color) -> Unit = {}
) {
    var currentColor by remember { mutableStateOf(colors.first()) }
    onColorChangeListener(currentColor)

    val colorSize = 35.dp

    LazyVerticalGrid(
        /*cells = GridCells.Fixed(10),*/
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
                    .clickable(true) {
                        onColorChangeListener(it)
                        currentColor = it
                    }
            )
        }
    }
}