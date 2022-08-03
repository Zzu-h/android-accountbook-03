package com.woowa.accountbook.domain.model

import androidx.compose.ui.graphics.Color
import com.woowa.accountbook.data.dto.CategoryDto
import java.io.Serializable

data class Category(
    val id: Int,
    val title: String,
    val color: Color,
    val type: String,
) : Serializable {
    fun toCategoryDto(): CategoryDto {
        return CategoryDto(
            id = id,
            title = title,
            color = color,
            type = type
        )
    }
}
