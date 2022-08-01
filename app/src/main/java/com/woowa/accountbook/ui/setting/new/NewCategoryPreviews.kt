package com.woowa.accountbook.ui.setting.new

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woowa.accountbook.ui.setting.new.component.ColorPaletteComponent
import com.woowa.accountbook.ui.theme.*

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