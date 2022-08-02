package com.woowa.accountbook.data.local.entity

import androidx.compose.ui.graphics.Color

data class DBCategory(
    val id: Int,
    val title: String,
    val color: Color,
    val type: String,
)
