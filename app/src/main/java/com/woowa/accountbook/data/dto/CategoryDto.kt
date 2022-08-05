package com.woowa.accountbook.data.dto

import androidx.compose.ui.graphics.Color
import com.woowa.accountbook.data.local.entity.DBCategory
import com.woowa.accountbook.domain.model.Category

data class CategoryDto(
    val id: Int,
    val title: String,
    val color: Color,
    val type: String,
)

fun CategoryDto.toDBCategory(): DBCategory {
    return DBCategory(
        id = id,
        title = title,
        color = color.value.toString(),
        type = type
    )
}

fun CategoryDto.toCategory(): Category {
    return Category(
        id = id,
        title = title,
        color = color,
        type = type
    )
}
