package com.woowa.accountbook.data.dto

import com.woowa.accountbook.data.local.entity.DBPayment

data class PaymentDto(
    val id: Int,
    val name: String
)

fun PaymentDto.toDBPayment(): DBPayment {
    return DBPayment(
        id = id,
        name = name,
    )
}