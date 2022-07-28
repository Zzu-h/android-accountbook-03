package com.woowa.accountbook.data.dto

import com.woowa.accountbook.domain.model.Income
import java.util.*

data class DBIncomeDto(
    val price: Int,
    val payment: String,
)

fun DBIncomeDto.toIncome(): Income {
    return Income(null, price = price,date = Date())
}