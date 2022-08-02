package com.woowa.accountbook.data.local.entity

import com.woowa.accountbook.data.dto.HistoryDto

data class DBHistory(
    val id: Int,
    val price: Int,
    val content: String?,
    val category: DBCategory,
    val year: Int,
    val month: Int,
    val day: Int,
    val payment: DBPayment,
    val type: String,
)

fun DBHistory.toHistoryDto(): HistoryDto {
    return HistoryDto(
        id = id,
        price = price,
        content = content,
        category = category.toCategoryDto(),
        year = year,
        month = month,
        day = day,
        type = type,
        payment = payment.toPaymentDto()
    )
}