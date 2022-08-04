package com.woowa.accountbook.ui.setting.manage.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.ui.theme.*

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun ColorPaletteComponentPreviews() {
    ColorPaletteComponent(
        listOf(
            Blue1,
            Blue2,
            Blue3,
            Blue4,
            Blue5,
            Green1,
            Green2,
            Green3,
            Green4,
            Green5,
            Purple1,
            Purple2,
            Purple3,
            Purple4,
            Purple5,
            Pink1,
            Pink2,
            Pink3,
            Pink4,
            Pink5,
            Olive1,
            Olive2,
            Olive3,
            Olive4,
            Olive5,
            Yellow1,
            Yellow2,
            Yellow3,
            Yellow4,
            Yellow5
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}