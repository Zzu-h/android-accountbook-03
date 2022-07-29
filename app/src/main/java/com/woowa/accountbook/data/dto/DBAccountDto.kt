package com.woowa.accountbook.data.dto

import com.woowa.accountbook.domain.model.Account

data class DBAccountDto(
    val price: Int,
    val payment: String,
    val content: String?,
    val year: Int = 2022,
    val month: Int = 7,
    val day: Int = 29,
    val type: Boolean
)

fun DBAccountDto.toAccount(): Account {
    return Account(
        content,
        price = price,
        payment = payment,
        year = year,
        month = month,
        day = day,
        type = type
    )
}