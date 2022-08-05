package com.woowa.accountbook.domain.model

import com.woowa.accountbook.data.dto.PaymentDto
import com.woowa.accountbook.utils.TypeFilter
import java.io.Serializable

data class Payment(
    val id: Int,
    val name: String,
) : Serializable {
    fun toPaymentDto(): PaymentDto {
        return PaymentDto(
            id = id,
            name = name,
            type = TypeFilter.EXPENDITURE
        )
    }
}
