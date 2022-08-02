package com.woowa.accountbook.data.local.entity

import com.woowa.accountbook.data.dto.PaymentDto

data class DBPayment(
    val id: Int,
    val name: String,
    val type: String
)

fun DBPayment.toPaymentDto(): PaymentDto {
    return PaymentDto(
        id = id,
        name = name,
        type = type
    )
}
