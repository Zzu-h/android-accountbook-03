package com.woowa.accountbook.domain.model

import androidx.compose.ui.graphics.Color

data class Category(
    val id: Int,
    val title: String,
    val color: Color,
    val type: String,
)
