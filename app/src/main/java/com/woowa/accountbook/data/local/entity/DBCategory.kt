package com.woowa.accountbook.data.local.entity

import androidx.compose.ui.graphics.Color
import com.woowa.accountbook.data.dto.CategoryDto

data class DBCategory(
    val id: Int,
    val title: String,
    val color: String,
    val type: String,
)

fun DBCategory.toCategoryDto(): CategoryDto {
    return CategoryDto(
        id = id,
        title = title,
        color = Color(color.toULong()),
        type = type
    )
}