package com.woowa.accountbook.data.dto

import com.woowa.accountbook.data.local.entity.DBHistory
import com.woowa.accountbook.domain.model.Account

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

fun HistoryDto.toAccount(): Account {
    return Account(
        content = content,
        price = price,
        payment = "temp",
        year = year,
        month = month,
        day = day,
        type = type
    )
}

fun HistoryDto.toDBHistory(): DBHistory {
    return DBHistory(
        id = id,
        price = price,
        payment = payment?.toDBPayment(),
        category = category.toDBCategory(),
        content = content,
        year = year,
        month = month,
        day = day,
        type = type,
    )
}