package com.woowa.accountbook.data.dto

import com.woowa.accountbook.domain.model.Expenditure
import java.util.*

data class DBExpenditureDto(
    val price: Int,
    val payment: String,
)

fun DBExpenditureDto.toExpenditure(): Expenditure {
    return Expenditure(null, price = price, payment = payment,date = Date())
}