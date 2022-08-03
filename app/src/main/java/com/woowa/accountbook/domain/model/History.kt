package com.woowa.accountbook.domain.model

import com.woowa.accountbook.data.dto.HistoryDto
import com.woowa.accountbook.data.dto.PaymentDto
import java.io.Serializable

data class History(
    val id: Int = -1,
    val content: String?,
    val payment: String,
    val category: Category,
    val price: Int,
    val year: Int,
    val month: Int,
    val day: Int,
    val type: String
) : Serializable {
    fun toHistoryDto(): HistoryDto {
        return HistoryDto(
            id = id,
            price = price,
            payment = PaymentDto(-1, payment, type = type),
            category = category.toCategoryDto(),
            content = content,
            year = year,
            month = month,
            day = day,
            type = type
        )
    }
}