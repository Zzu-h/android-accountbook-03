package com.woowa.accountbook.data.dto

import com.woowa.accountbook.data.local.entity.DBHistory
import com.woowa.accountbook.domain.model.History

data class HistoryDto(
    val id: Int,
    val price: Int,
    val payment: PaymentDto,
    val category: CategoryDto,
    val content: String?,
    val year: Int = 2022,
    val month: Int = 7,
    val day: Int = 29,
    val type: String
)

fun HistoryDto.toAccount(): History {
    return History(
        content = content,
        price = price,
        payment = payment.name,
        year = year,
        month = month,
        day = day,
        type = type,
        category = category.toCategory()
    )
}

fun HistoryDto.toDBHistory(): DBHistory {
    return DBHistory(
        id = id,
        price = price,
        payment = payment.toDBPayment(),
        category = category.toDBCategory(),
        content = content,
        year = year,
        month = month,
        day = day,
        type = type,
    )
}